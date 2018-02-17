package de.scout24.webanalyzerrest.service;

import de.scout24.webanalyzerrest.algorithm.Algorithm;
import de.scout24.webanalyzerrest.algorithm.config.AlgorithmFactory;
import de.scout24.webanalyzerrest.model.AnalysisInput;
import de.scout24.webanalyzerrest.model.AnalysisItem;
import de.scout24.webanalyzerrest.model.AnalysisItemFactory;
import de.scout24.webanalyzerrest.model.AnalysisOutput;
import de.scout24.webanalyzerrest.model.enums.AnalysisStatus;
import de.scout24.webanalyzerrest.model.enums.HtmlVersion;
import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import de.scout24.webanalyzerrest.repository.AnalysisOutputMemoryRepository;
import de.scout24.webanalyzerrest.repository.UrlAnalisysRestRepository;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class UrlAnalysisServiceImpl implements UrlAnalisysService {

    @Autowired
    private UrlAnalisysRestRepository repository;

    @Autowired
    private AlgorithmFactory factory;

    @Autowired
    @Qualifier("analysisOutputMemoryRepository")
    private AnalysisOutputMemoryRepository analysisOutputRepository;

    @Override
    public Map<ResponseItemType, AnalysisItem> analyseRemoteUrl(AnalysisInput input, String ip) throws Exception {
        if (input == null || StringUtils.isEmpty(input.getUrl())) {
            throw new Exception("Invalid input object");
        }
        Optional<String> htmlContents = repository.getHtmlFromUrl(input.getUrl());

        if (!htmlContents.isPresent()) {
            return new HashMap<>();
        }

        String html = htmlContents.get();
        Document dom = Jsoup.parse(html);

        // Hack to make links algorithm work and not make the code dirty passing URL's around
        dom.setBaseUri(input.getUrl());

        List<Algorithm> algorithms = factory.getAvailableAlgorithm();
        Map<ResponseItemType, AnalysisItem> resultMap = getAlgorithmResults(algorithms, dom);

        AnalysisOutput analysisOutput = new AnalysisOutput(AnalysisStatus.OK, resultMap);
        analysisOutputRepository.save(analysisOutput, ip);

        return analysisOutput.getItemsByAnalysisItem();
    }

    /**
     * Perform the algorithm analysis and returns a map with the results
     *
     * @param algorithms - algorithm list
     * @param dom        - HTML dom tree
     * @return map
     */
    private Map<ResponseItemType, AnalysisItem> getAlgorithmResults(List<Algorithm> algorithms, Document dom) {
        Map<ResponseItemType, AnalysisItem> result = new LinkedHashMap<>(); // To maintain the order of execution

        for (Algorithm algorithm : algorithms) {
            Object algorithmResult = algorithm.execute(dom);
            ResponseItemType itemType = algorithm.getItemType();
            AnalysisItem item = AnalysisItemFactory.getAnalysisType(algorithmResult, itemType);
            result.put(itemType, item);
        }

        return result;
    }

    @Deprecated
    private List<Algorithm> getAlgorithmList() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Algorithm> algorithms = new ArrayList<>();
        for (Class algorithm :
                factory.getAvailableAlgorithms(HtmlVersion.HTML5)) { // FIXME Remove hardcoded HTML version
            Constructor constructor = algorithm.getConstructor(); // Gets the default constructor
            Algorithm algorithmObj = (Algorithm) constructor.newInstance();
            algorithms.add(algorithmObj);
        }
        return algorithms;
    }

    @SuppressWarnings("unused")
    public void setRepository(UrlAnalisysRestRepository repository) {
        this.repository = repository;
    }

    public void setFactory(AlgorithmFactory factory) {
        this.factory = factory;
    }
}

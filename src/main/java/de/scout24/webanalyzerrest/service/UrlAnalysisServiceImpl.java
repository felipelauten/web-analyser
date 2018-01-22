package de.scout24.webanalyzerrest.service;

import de.scout24.webanalyzerrest.algorithm.Algorithm;
import de.scout24.webanalyzerrest.algorithm.AlgorithmFactory;
import de.scout24.webanalyzerrest.model.AnalysisInput;
import de.scout24.webanalyzerrest.model.AnalysisItem;
import de.scout24.webanalyzerrest.model.AnalysisOutput;
import de.scout24.webanalyzerrest.model.enums.AnalysisStatus;
import de.scout24.webanalyzerrest.model.enums.HtmlVersion;
import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import de.scout24.webanalyzerrest.repository.UrlAnalisysRestRepository;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public AnalysisOutput analyseRemoteUrl(AnalysisInput input) throws Exception {
        if (input == null || StringUtils.isEmpty(input.getUrl())) {
            throw new Exception("Invalid input object");
        }
        Optional<String> htmlContents = repository.getHtmlFromUrl(input.getUrl());

        if (!htmlContents.isPresent()) {
            return new AnalysisOutput(AnalysisStatus.UNKNOWN_ERROR);
        }

        String html = htmlContents.get();
        Document dom = Jsoup.parse(html);

        List<Algorithm> algorithms = getAlgorithmList();
        Map<ResponseItemType, AnalysisItem> resultMap = getAlgorithmResults(algorithms, dom);

        return new AnalysisOutput(AnalysisStatus.OK, resultMap);
    }

    private Map<ResponseItemType, AnalysisItem> getAlgorithmResults(List<Algorithm> algorithms, Document dom) throws Exception {
        Map<ResponseItemType, AnalysisItem> result = new LinkedHashMap<>(); // To maintain the order of execution

        for (Algorithm algorithm : algorithms) {
            Object algorithmResult = algorithm.execute(dom);
            result.put(algorithm.getItemType(), new AnalysisItem(algorithmResult));
        }

        return result;
    }

    private List<Algorithm> getAlgorithmList() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Algorithm> algorithms = new ArrayList<>();
        for (Class algorithm :
                factory.getAvailableAlgorithms(HtmlVersion.HTML5)) { // FIXME Remove hardcoded version
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
}

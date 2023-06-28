package com.devlauten.webanalyzerrest.service;

import com.devlauten.webanalyzerrest.algorithm.Algorithm;
import com.devlauten.webanalyzerrest.algorithm.config.AlgorithmFactory;
import com.devlauten.webanalyzerrest.model.*;
import com.devlauten.webanalyzerrest.model.enums.AnalysisStatus;
import com.devlauten.webanalyzerrest.model.enums.HtmlVersion;
import com.devlauten.webanalyzerrest.repository.AdditionalInformationDbRepository;
import com.devlauten.webanalyzerrest.repository.AnalysisItemDbRepository;
import com.devlauten.webanalyzerrest.repository.AnalysisOutputRepository;
import com.devlauten.webanalyzerrest.repository.UrlAnalisysRestRepository;
import com.devlauten.webanalyzerrest.model.enums.ResponseItemType;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UrlAnalysisServiceImpl implements UrlAnalisysService {

    @Autowired
    private UrlAnalisysRestRepository repository;

    @Autowired
    private AlgorithmFactory factory;

    @Autowired
    private AnalysisItemDbRepository itemRepository;

    @Autowired
    private AnalysisOutputRepository outputRepository;

    @Autowired
    private AdditionalInformationDbRepository additionalInformationRepository;

    @Override
    @Transactional
    public Map<ResponseItemType, AnalysisItem<?>> analyseRemoteUrl(AnalysisInput input, String ip) throws Exception {
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
        Map<ResponseItemType, AnalysisItem<?>> resultMap = getAlgorithmResults(algorithms, dom);

        AnalysisOutput analysisOutput = new AnalysisOutput(AnalysisStatus.OK, resultMap);
        outputRepository.save(analysisOutput);

        // Saves all additional information (if present)
        List<AdditionalInformation> informationList = resultMap.values().stream()
                .filter(AnalysisItem::isAdditionalInformationPresent)
                .map(AnalysisItem::getAdditionalInformation)
                .collect(Collectors.toList());
        additionalInformationRepository.saveAll(informationList);

        // Update the items with the current output
        resultMap.values().forEach(item -> item.setOutput(analysisOutput));

        // Save all the items and output result
        itemRepository.saveAll(resultMap.values());

        return analysisOutput.getItemsByAnalysisItem();
    }

    @Override
    public Map<String, AnalysisStatus> linkHealthCheck(Long analysisId) {
        if (Objects.isNull(analysisId) || Objects.equals(0L, analysisId)) {
            throw new RuntimeException("Provide the analysis ID");
        }
        Map<String, AnalysisStatus> map = new HashMap<>();
        Optional<AnalysisOutput> output = outputRepository.findById(analysisId);
        if (output.isEmpty()) {
            return Collections.emptyMap();
        }

        for (AnalysisItem<?> item: output.get().getAnalysisItems()) {
            if (ResponseItemType.ALL_LINKS_MAP.equals(item.getItemType())) {
                AnalysisItemMap itemMap = (AnalysisItemMap) item;
                Map<String, Integer> linkMap =  itemMap.getResultType();
                for (String link: linkMap.keySet()) {
                    if (repository.checkUrlConnectivity(link)) {
                        map.put(link, AnalysisStatus.OK);
                    } else {
                        map.put(link, AnalysisStatus.UNKNOWN_ERROR);
                    }
                }
            }
        }

        return map;
    }

    /**
     * Perform the algorithm analysis and returns a map with the results
     *
     * @param algorithms - algorithm list
     * @param dom        - HTML dom tree
     * @return map
     */
    private Map<ResponseItemType, AnalysisItem<?>> getAlgorithmResults(List<Algorithm> algorithms, Document dom) {
        Map<ResponseItemType, AnalysisItem<?>> result = new LinkedHashMap<>(); // To maintain the order of execution

        for (Algorithm algorithm : algorithms) {
            result.put(algorithm.getItemType(), algorithm.execute(dom));
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
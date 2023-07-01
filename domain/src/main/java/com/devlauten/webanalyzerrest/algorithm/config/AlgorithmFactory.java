package com.devlauten.webanalyzerrest.algorithm.config;

import com.devlauten.webanalyzer.data.entities.enums.HtmlVersion;
import com.devlauten.webanalyzerrest.algorithm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class AlgorithmFactory {

    @Autowired
    @Qualifier(HtmlPageTitleAlgorithm.ALGORITHM_NAME)
    private Algorithm<String> htmlPageTitleAlgorithm;

    @Autowired
    @Qualifier(InternalLinksAlgorithm.ALGORITHM_NAME)
    private Algorithm<Integer> internalLinksAlgorithm;

    @Autowired
    @Qualifier(ExternalLinksAlgorithm.ALGORITHM_NAME)
    private Algorithm<Integer> externalLinksAlgorithm;

    @Autowired
    @Qualifier(HeadingsAlgorithm.ALGORITHM_NAME)
    private Algorithm<Map<String, Integer>> headingsLinksAlgorithm;

    @Autowired
    @Qualifier(LoginFormAlgorithm.ALGORITHM_NAME)
    private Algorithm<Boolean> loginFormAlgorithm;

    @Autowired
    @Qualifier(AllLinksAlgorithm.ALGORITHM_NAME)
    private Algorithm<Map<String, Integer>> allLinksAlgorithm;

    /**
     * Previous mechanism to get the algorithm list.
     * Returns class refereces to instantiate via reflection
     *
     * @param version
     * @return
     */
    @Deprecated
    public List<Class<? extends Algorithm<?>>> getAvailableAlgorithms(HtmlVersion version) {
        return Arrays.asList(HtmlPageTitleAlgorithm.class, InternalLinksAlgorithm.class,
                ExternalLinksAlgorithm.class, HeadingsAlgorithm.class, LoginFormAlgorithm.class);
    }

    public List<Algorithm<?>> getAvailableAlgorithm() {
        return Arrays.asList(htmlPageTitleAlgorithm, internalLinksAlgorithm, externalLinksAlgorithm,
                allLinksAlgorithm, headingsLinksAlgorithm, loginFormAlgorithm);
    }

}

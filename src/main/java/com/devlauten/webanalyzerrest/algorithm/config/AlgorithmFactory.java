package com.devlauten.webanalyzerrest.algorithm.config;

import com.devlauten.webanalyzerrest.algorithm.*;
import com.devlauten.webanalyzerrest.model.enums.HtmlVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AlgorithmFactory {

    @Autowired
    @Qualifier(HtmlPageTitleAlgorithm.ALGORITHM_NAME)
    private Algorithm htmlPageTitleAlgorithm;

    @Autowired
    @Qualifier(InternalLinksAlgorithm.ALGORITHM_NAME)
    private Algorithm internalLinksAlgorithm;

    @Autowired
    @Qualifier(ExternalLinksAlgorithm.ALGORITHM_NAME)
    private Algorithm externalLinksAlgorithm;

    @Autowired
    @Qualifier(HeadingsAlgorithm.ALGORITHM_NAME)
    private Algorithm headingsLinksAlgorithm;

    @Autowired
    @Qualifier(LoginFormAlgorithm.ALGORITHM_NAME)
    private Algorithm loginFormAlgorithm;

    @Autowired
    @Qualifier(AllLinksAlgorithm.ALGORITHM_NAME)
    private Algorithm allLinksAlgorithm;

    /**
     * Previous mechanism to get the algorithm list.
     * Returns class refereces to instantiate via reflection
     *
     * @param version
     * @return
     */
    @Deprecated
    public List<Class> getAvailableAlgorithms(HtmlVersion version) {
        return Arrays.asList(HtmlPageTitleAlgorithm.class, InternalLinksAlgorithm.class,
                ExternalLinksAlgorithm.class, HeadingsAlgorithm.class, LoginFormAlgorithm.class);
    }

    public List<Algorithm> getAvailableAlgorithm() {
        return Arrays.asList(htmlPageTitleAlgorithm, internalLinksAlgorithm, externalLinksAlgorithm,
                allLinksAlgorithm, headingsLinksAlgorithm, loginFormAlgorithm);
    }

}
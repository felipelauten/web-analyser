package de.scout24.webanalyzerrest.algorithm.config;

import de.scout24.webanalyzerrest.algorithm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgorithmConfiguration {

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

    @Bean
    public Algorithm getHtmlPageTitleAlgorithm() {
        return htmlPageTitleAlgorithm;
    }

    @Bean
    public Algorithm getInternalLinksAlgorithm() {
        return internalLinksAlgorithm;
    }

    @Bean
    public Algorithm getExternalLinksAlgorithm() {
        return externalLinksAlgorithm;
    }

    @Bean
    public Algorithm getHeadingsLinksAlgorithm() {
        return headingsLinksAlgorithm;
    }

    @Bean
    public Algorithm getLoginFormAlgorithm() {
        return loginFormAlgorithm;
    }
}

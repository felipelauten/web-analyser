package de.scout24.webanalyzerrest.algorithm;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgorithmConfiguration {

    @Bean
    @Qualifier(EmptyAlgorithm.ALGORITHM_NAME)
    public Algorithm emptyAlgorithm() {
        return new EmptyAlgorithm();
    }

    @Bean
    @Qualifier(HtmlPageTitleAlgorithm.ALGORITHM_NAME)
    public Algorithm htmlPageTitleAlgorithm() {
        return new EmptyAlgorithm();
    }
}

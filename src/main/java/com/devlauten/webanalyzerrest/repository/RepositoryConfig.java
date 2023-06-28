package com.devlauten.webanalyzerrest.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public AnalysisOutputMemoryRepository memoryRepository() {
        return new AnalysisOutputMemoryRepository();
    }

}

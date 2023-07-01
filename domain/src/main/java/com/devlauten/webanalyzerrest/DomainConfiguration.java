package com.devlauten.webanalyzerrest;

import com.devlauten.webanalyzerrest.ports.UrlAnalysisService;
import com.devlauten.webanalyzerrest.service.UrlAnalysisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Autowired
    UrlAnalysisServiceImpl urlAnalysisServiceImpl;

    @Bean
    public UrlAnalysisService service() {
        return urlAnalysisServiceImpl;
    }

}

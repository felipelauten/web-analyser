package de.scout24.webanalyzerrest.algorithm;

import de.scout24.webanalyzerrest.model.enums.HtmlVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AlgorithmFactory {

    @Autowired
    private Algorithm emptyAlgorithm;
    @Autowired
    private Algorithm html5PageTitleAlgorithm;

    public List<Class> getAvailableAlgorithms(HtmlVersion version) {
        if (HtmlVersion.HTML5.equals(version)) {
            return Arrays.asList(HtmlPageTitleAlgorithm.class);
        }
        return Arrays.asList(EmptyAlgorithm.class);
    }

    public Algorithm getPageTitleAlgorithm(HtmlVersion version) {
        if (HtmlVersion.HTML5.equals(version)) {
            return html5PageTitleAlgorithm;
        }
        return emptyAlgorithm;
    }

    @Bean
    public Algorithm emptyAlgorithm() {
        return new EmptyAlgorithm();
    }

    @Bean
    public Algorithm html5PageTitleAlgorithm() {
        return new EmptyAlgorithm();
    }
}

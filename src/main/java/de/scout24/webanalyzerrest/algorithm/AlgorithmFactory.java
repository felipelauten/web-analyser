package de.scout24.webanalyzerrest.algorithm;

import de.scout24.webanalyzerrest.model.enums.HtmlVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AlgorithmFactory {

    @Autowired
    @Qualifier(EmptyAlgorithm.ALGORITHM_NAME)
    private Algorithm emptyAlgorithm;

    @Autowired
    @Qualifier(HtmlPageTitleAlgorithm.ALGORITHM_NAME)
    private Algorithm htmlPageTitleAlgorithm;

    public List<Class> getAvailableAlgorithms(HtmlVersion version) {
        return Arrays.asList(HtmlPageTitleAlgorithm.class);
    }

    public Algorithm getPageTitleAlgorithm(HtmlVersion version) {
        if (HtmlVersion.HTML5.equals(version)) {
            return htmlPageTitleAlgorithm;
        }
        return emptyAlgorithm;
    }

}

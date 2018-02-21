package de.scout24.webanalyzerrest.algorithm.config;

import de.scout24.webanalyzerrest.algorithm.*;
import de.scout24.webanalyzerrest.model.enums.HtmlVersion;
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
                headingsLinksAlgorithm, loginFormAlgorithm);
    }

}

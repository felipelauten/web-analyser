package de.scout24.webanalyzerrest.algorithm;

import de.scout24.webanalyzerrest.algorithm.exception.AlgoruthmException;
import de.scout24.webanalyzerrest.model.AnalysisItem;
import de.scout24.webanalyzerrest.model.AnalysisItemString;
import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.lang.model.util.Elements;
import java.util.List;
import java.util.Optional;

@Component
@Qualifier(HtmlPageTitleAlgorithm.ALGORITHM_NAME)
public class HtmlPageTitleAlgorithm implements Algorithm {

    public static final String ALGORITHM_NAME = "htmlPageTitleAlgorithm";

    private static final String TITLE_TAG = "title";
    private static Logger LOG = LoggerFactory.getLogger(HtmlPageTitleAlgorithm.class.getName());

    @Override
    public AnalysisItem execute(Document dom) throws AlgoruthmException {
        return dom.getAllElements().stream()
                .map(element -> findByTagName(element, TITLE_TAG))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .map(this::getAnalysisItem)
                .orElseGet(this::emptyAnalysisItem);
    }

    private AnalysisItemString getAnalysisItem(String content) {
        return new AnalysisItemString(content, ResponseItemType.NUMBER_OF_HEADINGS);
    }

    private AnalysisItemString emptyAnalysisItem() {
        return new AnalysisItemString(StringUtils.EMPTY, ResponseItemType.NUMBER_OF_HEADINGS);
    }

    @Override
    public ResponseItemType getItemType() {
        return ResponseItemType.PAGE_TITLE;
    }
}

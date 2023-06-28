package com.devlauten.webanalyzerrest.algorithm;

import com.devlauten.webanalyzerrest.algorithm.exception.AlgorithmException;
import com.devlauten.webanalyzerrest.model.AnalysisItem;
import com.devlauten.webanalyzerrest.model.AnalysisItemString;
import com.devlauten.webanalyzerrest.model.enums.ResponseItemType;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Qualifier(HtmlPageTitleAlgorithm.ALGORITHM_NAME)
public class HtmlPageTitleAlgorithm implements Algorithm<String> {

    public static final String ALGORITHM_NAME = "htmlPageTitleAlgorithm";

    private static final String TITLE_TAG = "title";

    @Override
    public AnalysisItem<String> execute(Document dom) throws AlgorithmException {
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
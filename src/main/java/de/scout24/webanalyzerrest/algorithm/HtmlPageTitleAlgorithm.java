package de.scout24.webanalyzerrest.algorithm;

import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HtmlPageTitleAlgorithm implements Algorithm<String> {

    public static final String ALGORITHM_NAME = "htmlPageTitleAlgorithm";

    private static final String TITLE_TAG = "title";
    private static Logger LOG = LoggerFactory.getLogger(HtmlPageTitleAlgorithm.class.getName());

    @Override
    public String execute(Document dom) {
        for (Element e : dom.getAllElements()) {
            Optional<String> elementText = findByTagName(e, TITLE_TAG);
            if (elementText.isPresent()) {
                String content = elementText.get();
                logInformation(LOG, "Found %s tag, content [%s]", TITLE_TAG, content);
                return content;
            }
        }

        return StringUtils.EMPTY;
    }

    @Override
    public ResponseItemType getItemType() {
        return ResponseItemType.PAGE_TITLE;
    }
}

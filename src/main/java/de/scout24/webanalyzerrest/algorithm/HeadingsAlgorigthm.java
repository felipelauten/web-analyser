package de.scout24.webanalyzerrest.algorithm;

import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HeadingsAlgorigthm implements Algorithm<Map<String, Integer>> {
    @Override
    public Map<String, Integer> execute(Document dom) {
        Map<String, Integer> result = new LinkedHashMap<>();    // To maintain the order
        for (String headingType : getListOfHeadings()) {
            result.put(headingType, countNumberOfItems(headingType, dom));
        }

        return result;
    }

    List<String> getListOfHeadings() {
        return Arrays.asList("h1", "h2", "h3", "h4", "h5", "h6");
    }

    int countNumberOfItems(String tag, Document dom) {
        return dom.select(tag).size();
    }

    @Override
    public ResponseItemType getItemType() {
        return ResponseItemType.NUMBER_OF_HEADINGS;
    }
}

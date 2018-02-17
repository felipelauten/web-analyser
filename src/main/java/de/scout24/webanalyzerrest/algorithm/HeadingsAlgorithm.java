package de.scout24.webanalyzerrest.algorithm;

import de.scout24.webanalyzerrest.algorithm.exception.AlgoruthmException;
import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Qualifier(HeadingsAlgorithm.ALGORITHM_NAME)
public class HeadingsAlgorithm implements Algorithm<Map<String, Integer>> {

    public static final String ALGORITHM_NAME = "headingsAlgorigthm";

    @Override
    public Map<String, Integer> execute(Document dom) throws AlgoruthmException {
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

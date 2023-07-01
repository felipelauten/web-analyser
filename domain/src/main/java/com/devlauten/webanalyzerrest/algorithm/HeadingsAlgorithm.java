package com.devlauten.webanalyzerrest.algorithm;

import com.devlauten.webanalyzer.data.entities.AnalysisItem;
import com.devlauten.webanalyzer.data.entities.AnalysisItemMap;
import com.devlauten.webanalyzer.data.entities.enums.ResponseItemType;
import com.devlauten.webanalyzerrest.algorithm.exception.AlgorithmException;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Qualifier(HeadingsAlgorithm.ALGORITHM_NAME)
public class HeadingsAlgorithm implements Algorithm<Map<String, Integer>> {

    public static final String ALGORITHM_NAME = "headingsAlgorigthm";
    private static final List<String> HEADER_TAGS = Arrays.asList("h1", "h2", "h3", "h4", "h5", "h6");

    @Override
    public AnalysisItem<Map<String, Integer>> execute(Document dom) throws AlgorithmException {
        Map<String, Integer> result = HEADER_TAGS.stream()
                .collect(Collectors.toMap(Function.identity(), header -> countNumberOfItems(header, dom)));

        return new AnalysisItemMap(result, ResponseItemType.NUMBER_OF_HEADINGS);
    }

    int countNumberOfItems(String tag, Document dom) {
        return dom.select(tag).size();
    }

    @Override
    public ResponseItemType getItemType() {
        return ResponseItemType.NUMBER_OF_HEADINGS;
    }
}

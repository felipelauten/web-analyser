package de.scout24.webanalyzerrest.algorithm;

import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.jsoup.nodes.Document;

public class EmptyAlgorithm implements Algorithm<Boolean, ResponseItemType> {

    public static final String ALGORITHM_NAME = "emptyAlgorithm";

    @Override
    public Boolean execute(Document dom) {
        return true;
    }

    @Override
    public ResponseItemType getItemType() {
        return null;
    }
}

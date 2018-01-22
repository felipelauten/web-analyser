package de.scout24.webanalyzerrest.algorithm;

import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.jsoup.nodes.Document;

public class EmptyAlgorithm implements Algorithm<Boolean, ResponseItemType> {

    @Override
    public Boolean execute(Document dom) {
        return true;
    }

    @Override
    public ResponseItemType getItemType() {
        return null;
    }
}

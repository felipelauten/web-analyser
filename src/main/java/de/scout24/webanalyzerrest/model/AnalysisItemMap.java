package de.scout24.webanalyzerrest.model;

import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.hibernate.annotations.Target;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Map;

public class AnalysisItemMap extends AnalysisItem<Map<String, Integer>> {

    @Enumerated(value = EnumType.ORDINAL)
    private ResponseItemType itemType;
    @Target(Boolean.class)
    private Map<String, Integer> resultType;
    @ManyToOne
    private AnalysisOutput output;

    public AnalysisItemMap(Map<String, Integer> result, ResponseItemType itemType) {
        this.setResultType(result);
        this.setItemType(itemType);
    }

    @Override
    public ResponseItemType getItemType() {
        return null;
    }

    @Override
    protected void setItemType(ResponseItemType itemType) {

    }

    @Override
    public void setOutput(AnalysisOutput output) {

    }

    @Override
    public Map<String, Integer> getResultType() {
        return null;
    }

    @Override
    protected void setResultType(Map<String, Integer> resultType) {

    }
}

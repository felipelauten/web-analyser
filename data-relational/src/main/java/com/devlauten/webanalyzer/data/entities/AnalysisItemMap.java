package com.devlauten.webanalyzer.data.entities;

import com.devlauten.webanalyzer.data.entities.enums.ResponseItemType;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Map;

@Entity
public class AnalysisItemMap extends AnalysisItem<Map<String, Integer>> {

    @Enumerated(value = EnumType.ORDINAL)
    private ResponseItemType itemType;
    @ElementCollection(fetch = FetchType.EAGER)

    @Fetch(FetchMode.SELECT)
    private Map<String, Integer> resultType;
    @ManyToOne
    private AnalysisOutput output;

    protected AnalysisItemMap() {
        // for JPA
    }

    public AnalysisItemMap(Map<String, Integer> result, ResponseItemType itemType) {
        this.setResultType(result);
        this.setItemType(itemType);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public ResponseItemType getItemType() {
        return itemType;
    }

    @Override
    protected void setItemType(ResponseItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public void setOutput(AnalysisOutput output) {
        this.output = output;
    }

    @Override
    public Map<String, Integer> getResultType() {
        return resultType;
    }

    @Override
    protected void setResultType(Map<String, Integer> resultType) {
        this.resultType = resultType;
    }
}

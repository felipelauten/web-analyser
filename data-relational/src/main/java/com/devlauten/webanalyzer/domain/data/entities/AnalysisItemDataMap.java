package com.devlauten.webanalyzer.domain.data.entities;

import com.devlauten.webanalyzer.domain.data.entities.enums.ResponseItemType;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Map;

@Entity
public class AnalysisItemDataMap extends AnalysisItemData<Map<String, Integer>> {

    @Enumerated(value = EnumType.ORDINAL)
    private ResponseItemType itemType;
    @ElementCollection(fetch = FetchType.EAGER)

    @Fetch(FetchMode.SELECT)
    private Map<String, Integer> result;
    @ManyToOne
    private AnalysisOutput output;

    protected AnalysisItemDataMap() {
        // for JPA
    }

    public AnalysisItemDataMap(Map<String, Integer> result, ResponseItemType itemType) {
        this.setResult(result);
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
    public Map<String, Integer> getResult() {
        return result;
    }

    @Override
    protected void setResult(Map<String, Integer> result) {
        this.result = result;
    }
}

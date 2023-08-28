package com.devlauten.webanalyzer.domain.data.entities;

import com.devlauten.webanalyzer.domain.data.entities.enums.ResponseItemType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

@Entity
@JsonAutoDetect
public class AnalysisItemDataInteger extends AnalysisItemData<Integer> {

    @Enumerated(value = EnumType.ORDINAL)
    private ResponseItemType itemType;
    @ManyToOne
    private AnalysisOutput output;

    @Column(name = "resultTypeInteger")
    private Integer result;

    protected AnalysisItemDataInteger() {
        // for JPA
    }

    public AnalysisItemDataInteger(Integer result, ResponseItemType itemType) {
        this.setResult(result);
        this.setItemType(itemType);
    }

    public ResponseItemType getItemType() {
        return itemType;
    }

    @Override
    protected void setItemType(ResponseItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public Integer getResult() {
        return result;
    }

    @Override
    protected void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public void setOutput(AnalysisOutput output) {
        this.output = output;
    }
}

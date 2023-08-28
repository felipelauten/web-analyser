package com.devlauten.webanalyzer.domain.data.entities;

import com.devlauten.webanalyzer.domain.data.entities.enums.ResponseItemType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

@Entity
@JsonAutoDetect
public class AnalysisItemDataBoolean extends AnalysisItemData<Boolean> {

    @Enumerated(value = EnumType.ORDINAL)
    private ResponseItemType itemType;
    @Column(name = "resultTypeBoolean")
    private Boolean result;
    @ManyToOne
    private AnalysisOutput output;

    protected AnalysisItemDataBoolean() {
        // for JPA
    }

    public AnalysisItemDataBoolean(Boolean result, ResponseItemType itemType) {
        this.setResult(result);
        this.setItemType(itemType);
    }

    public AnalysisItemDataBoolean(Boolean result) {
        this.setResult(result);
    }

    public ResponseItemType getItemType() {
        return itemType;
    }

    @Override
    protected void setItemType(ResponseItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public Boolean getResult() {
        return result;
    }

    @Override
    protected void setResult(Boolean result) {
        this.result = result;
    }

    @Override
    public void setOutput(AnalysisOutput output) {
        this.output = output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalysisItemDataBoolean)) return false;
        AnalysisItemDataBoolean that = (AnalysisItemDataBoolean) o;
        return getItemType() == that.getItemType() &&
                Objects.equals(getResult(), that.getResult()) &&
                Objects.equals(output, that.output);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getItemType(), getResult(), output);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

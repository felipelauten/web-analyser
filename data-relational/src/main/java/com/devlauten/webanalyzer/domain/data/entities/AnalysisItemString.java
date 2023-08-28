package com.devlauten.webanalyzer.domain.data.entities;

import com.devlauten.webanalyzer.domain.data.entities.enums.ResponseItemType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

@Entity
@JsonAutoDetect
public class AnalysisItemString extends AnalysisItemData<String> {

    @Enumerated(value = EnumType.ORDINAL)
    private ResponseItemType itemType;
    @Column(name = "resultTypeString")
    private String result;
    @ManyToOne
    private AnalysisOutput output;

    protected AnalysisItemString() {
        // for JPA
    }

    public AnalysisItemString(String result, ResponseItemType itemType) {
        this.setResult(result);
        this.setItemType(itemType);
    }

    @Override
    public ResponseItemType getItemType() {
        return null;
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
    public String getResult() {
        return result;
    }

    @Override
    protected void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalysisItemString)) return false;
        AnalysisItemString that = (AnalysisItemString) o;
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

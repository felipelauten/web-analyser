package com.devlauten.webanalyzerrest.model;

import com.devlauten.webanalyzerrest.model.enums.ResponseItemType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

@Entity
@JsonAutoDetect
public class AnalysisItemString extends AnalysisItem<String> {

    @Enumerated(value = EnumType.ORDINAL)
    private ResponseItemType itemType;
    @Column(name = "resultTypeString")
    private String resultType;
    @ManyToOne
    private AnalysisOutput output;

    protected AnalysisItemString() {
        // for JPA
    }

    public AnalysisItemString(String result, ResponseItemType itemType) {
        this.setResultType(result);
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
    public String getResultType() {
        return resultType;
    }

    @Override
    protected void setResultType(String resultType) {
        this.resultType = resultType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalysisItemString)) return false;
        AnalysisItemString that = (AnalysisItemString) o;
        return getItemType() == that.getItemType() &&
                Objects.equals(getResultType(), that.getResultType()) &&
                Objects.equals(output, that.output);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getItemType(), getResultType(), output);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

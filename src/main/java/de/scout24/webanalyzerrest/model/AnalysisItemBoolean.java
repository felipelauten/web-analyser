package de.scout24.webanalyzerrest.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Target;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@JsonAutoDetect
public class AnalysisItemBoolean extends AnalysisItem<Boolean> {

    @Enumerated(value = EnumType.ORDINAL)
    private ResponseItemType itemType;
    @Target(Boolean.class)
    private Boolean resultType;
    @ManyToOne
    private AnalysisOutput output;

    public AnalysisItemBoolean(Boolean result, ResponseItemType itemType) {
        this.setResultType(result);
        this.setItemType(itemType);
    }

    public AnalysisItemBoolean(Boolean result) {
        this.setResultType(result);
    }

    public ResponseItemType getItemType() {
        return itemType;
    }

    @Override
    protected void setItemType(ResponseItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public Boolean getResultType() {
        return resultType;
    }

    @Override
    protected void setResultType(Boolean resultType) {
        this.resultType = resultType;
    }

    @Override
    public void setOutput(AnalysisOutput output) {
        this.output = output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalysisItemBoolean)) return false;
        AnalysisItemBoolean that = (AnalysisItemBoolean) o;
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

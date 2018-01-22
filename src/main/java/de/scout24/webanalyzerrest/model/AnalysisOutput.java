package de.scout24.webanalyzerrest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.scout24.webanalyzerrest.model.enums.AnalysisStatus;
import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * Provides the Output of the analysis.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
public class AnalysisOutput implements Serializable {

    private AnalysisStatus status;
    private Map<ResponseItemType, AnalysisItem> itemsByAnalisysItem;

    public AnalysisOutput() {
        // For Jackson XML
    }

    public AnalysisOutput(AnalysisStatus status) {
        this.status = status;
    }

    public AnalysisOutput(AnalysisStatus status, Map<ResponseItemType, AnalysisItem> itemsByAnalisysItem) {
        this.status = status;
        this.itemsByAnalisysItem = itemsByAnalisysItem;
    }

    public AnalysisStatus getStatus() {
        return status;
    }

    public void setStatus(AnalysisStatus status) {
        this.status = status;
    }

    public Map<ResponseItemType, AnalysisItem> getItemsByAnalisysItem() {
        return itemsByAnalisysItem;
    }

    public void setItemsByAnalisysItem(Map<ResponseItemType, AnalysisItem> itemsByAnalisysItem) {
        this.itemsByAnalisysItem = itemsByAnalisysItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalysisOutput)) return false;
        AnalysisOutput that = (AnalysisOutput) o;
        return getStatus() == that.getStatus() &&
                Objects.equals(getItemsByAnalisysItem(), that.getItemsByAnalisysItem());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getStatus(), getItemsByAnalisysItem());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

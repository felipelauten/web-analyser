package com.devlauten.webanalyzer.domain.data.entities;

import com.devlauten.webanalyzer.domain.data.entities.enums.AnalysisStatus;
import com.devlauten.webanalyzer.domain.data.entities.enums.ResponseItemType;
import jakarta.persistence.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Provides the Output of the analysis.
 */
@Entity
@Table(name = "ANALYSIS_OUTPUT")
@SuppressWarnings("unused")
public class AnalysisOutput implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.ORDINAL)
    private AnalysisStatus status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "output")
    private List<AnalysisItemData<?>> analysisItemData;

    @Transient
    private Map<ResponseItemType, AnalysisItemData<?>> itemsByAnalysisItem;

    public AnalysisOutput() {
        // For JPA

    }

    public AnalysisOutput(AnalysisStatus status) {
        this.status = status;
    }

    public AnalysisOutput(AnalysisStatus status, Map<ResponseItemType, AnalysisItemData<?>> itemsByAnalysisItem) {
        this.status = status;
        this.itemsByAnalysisItem = itemsByAnalysisItem;
        buildList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnalysisStatus getStatus() {
        return status;
    }

    public void setStatus(AnalysisStatus status) {
        this.status = status;
    }

    public List<AnalysisItemData<?>> getAnalysisItems() {
        return analysisItemData;
    }

    public void setAnalysisItems(List<AnalysisItemData<?>> analysisItemData) {
        this.analysisItemData = analysisItemData;
    }

    public Map<ResponseItemType, AnalysisItemData<?>> getItemsByAnalysisItem() {
        return itemsByAnalysisItem;
    }

    public void setItemsByAnalysisItem(Map<ResponseItemType, AnalysisItemData<?>> itemsByAnalysisItem) {
        this.itemsByAnalysisItem = itemsByAnalysisItem;
    }

    private void buildMap() {
        if (this.itemsByAnalysisItem != null && CollectionUtils.isNotEmpty(this.analysisItemData)) {
            itemsByAnalysisItem = analysisItemData.stream().collect(Collectors.toMap(AnalysisItemData::getItemType, v -> v));
        }
    }

    private void buildList() {
        if (MapUtils.isNotEmpty(itemsByAnalysisItem)) {
            this.analysisItemData =
                    itemsByAnalysisItem.keySet().stream()
                            .map(key -> itemsByAnalysisItem.get(key))
                            .collect(Collectors.toList());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalysisOutput)) return false;
        AnalysisOutput that = (AnalysisOutput) o;
        return getStatus() == that.getStatus() &&
                Objects.equals(getItemsByAnalysisItem(), that.getItemsByAnalysisItem());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getStatus(), getItemsByAnalysisItem());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

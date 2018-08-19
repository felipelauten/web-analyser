package de.scout24.webanalyzerrest.model;

import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Cache;
import javax.persistence.Entity;
import java.util.Map;

@Entity
public class AnalysisItemMap extends AnalysisItem<Map<String, Integer>> {

    @Id
    private Long id;
    @Enumerated(value = EnumType.ORDINAL)
    private ResponseItemType itemType;
    @Target(Map.class)
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "analysis_item_map", joinColumns = @JoinColumn(name = "result_type_id", nullable = false))
    @MapKeyColumn(name = "link", length = 5000, nullable = false)
    @MapKeyClass(String.class)
    @BatchSize(size = 100)
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

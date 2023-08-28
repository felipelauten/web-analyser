package com.devlauten.webanalyzer.domain.data.entities;

import com.devlauten.webanalyzer.domain.data.entities.enums.ResponseItemType;
import jakarta.persistence.*;

/**
 * Data object containing the analysis item
 *
 * @param <Type> of evaluated value
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AnalysisItemData<Type> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "output")
    private AnalysisOutput output;

    @OneToOne(fetch = FetchType.LAZY , cascade = { CascadeType.REMOVE })
    private AdditionalInformation additionalInformation;

    protected AnalysisItemData() {
        // for JPA
    }

    public AnalysisItemData(AnalysisOutput output, Type algorithmResult, ResponseItemType itemType) {
        this.output = output;
        this.setResult(algorithmResult);
        this.setItemType(itemType);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract ResponseItemType getItemType();

    protected abstract void setItemType(ResponseItemType itemType);

    public abstract void setOutput(AnalysisOutput output);

    public abstract Type getResult();

    protected abstract void setResult(Type result);

    public boolean isAdditionalInformationPresent() { return additionalInformation != null; }

    public AdditionalInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String toString() {
        return "AnalysisItemData{" +
                "id=" + id +
                '}';
    }
}

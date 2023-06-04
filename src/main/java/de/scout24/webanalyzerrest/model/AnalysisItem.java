package de.scout24.webanalyzerrest.model;

import de.scout24.webanalyzerrest.model.enums.ResponseItemType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Generic object containing the analysis item
 *
 * @param <Type> of evaluated value
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AnalysisItem<Type> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "output")
    private AnalysisOutput output;

    @OneToOne(fetch = FetchType.LAZY , cascade = { CascadeType.REMOVE })
    private AdditionalInformation additionalInformation;

    protected AnalysisItem() {
        // for JPA
    }

    public AnalysisItem(AnalysisOutput output, Type algorithmResult, ResponseItemType itemType) {
        this.output = output;
        this.setResultType(algorithmResult);
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

    public abstract Type getResultType();

    protected abstract void setResultType(Type resultType);

    public boolean isAdditionalInformationPresent() { return additionalInformation != null; }

    public AdditionalInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

}

package de.scout24.webanalyzerrest.model;

import java.util.Objects;

/**
 * Generic object containing the analysis item
 *
 * @param <Type> of evaluated value
 */
public class AnalysisItem<Type> {

    private Type resultType;
    private AdditionalInformation additionalInformation;

    public AnalysisItem(Type result) {
        this.resultType = result;
    }

    public Type getResultType() {
        return resultType;
    }

    public void setResultType(Type resultType) {
        this.resultType = resultType;
    }

    public AdditionalInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalysisItem)) return false;
        AnalysisItem<?> that = (AnalysisItem<?>) o;
        return Objects.equals(getResultType(), that.getResultType()) &&
                Objects.equals(getAdditionalInformation(), that.getAdditionalInformation());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getResultType(), getAdditionalInformation());
    }
}

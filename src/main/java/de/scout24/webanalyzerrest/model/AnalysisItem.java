package de.scout24.webanalyzerrest.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

/**
 * Generic object containing the analysis item
 *
 * @param <Type> of evaluated value
 */
public class AnalysisItem<Type> {

    private Type resultType;

    public AnalysisItem(Type result) {
        this.resultType = result;
    }

    public Type getResultType() {
        return resultType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisItem<?> that = (AnalysisItem<?>) o;
        return Objects.equals(getResultType(), that.getResultType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResultType());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

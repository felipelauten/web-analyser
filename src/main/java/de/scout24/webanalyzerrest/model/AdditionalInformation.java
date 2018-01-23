package de.scout24.webanalyzerrest.model;

import de.scout24.webanalyzerrest.model.enums.AdditionalInformationType;

import java.io.Serializable;
import java.util.Objects;

public class AdditionalInformation implements Serializable {

    private String comment;
    private String url;
    private AdditionalInformationType type;

    public AdditionalInformation(String comment, String url, AdditionalInformationType type) {
        this.comment = comment;
        this.url = url;
        this.type = type;
    }

    public AdditionalInformation(String url, AdditionalInformationType type) {
        this.url = url;
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AdditionalInformationType getType() {
        return type;
    }

    public void setType(AdditionalInformationType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdditionalInformation)) return false;
        AdditionalInformation that = (AdditionalInformation) o;
        return Objects.equals(getUrl(), that.getUrl()) &&
                getType() == that.getType();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUrl(), getType());
    }
}

package com.devlauten.webanalyzer.data.entities;

import com.devlauten.webanalyzer.data.entities.enums.AdditionalInformationType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ADDITIONAL_INFORMATION")
public class AdditionalInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private String url;
    private AdditionalInformationType type;

    protected AdditionalInformation() {
        // for JPA
    }

    public AdditionalInformation(String comment, String url, AdditionalInformationType type) {
        this.comment = comment;
        this.url = url;
        this.type = type;
    }

    public AdditionalInformation(String url, AdditionalInformationType type) {
        this.url = url;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

package com.devlauten.webanalyzer.domain.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalysisInput implements Serializable {

    private String url;
    private Date analysisStartDate;

    public AnalysisInput() {
        analysisStartDate = new Date();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getAnalysisStartDate() {
        return analysisStartDate;
    }

    private void setAnalysisStartDate(Date date) {
        this.analysisStartDate = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisInput that = (AnalysisInput) o;
        return Objects.equals(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

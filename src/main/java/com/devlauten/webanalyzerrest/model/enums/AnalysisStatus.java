package com.devlauten.webanalyzerrest.model.enums;

/**
 * Enum that provides the possible error codes
 */
@SuppressWarnings("unused")
public enum AnalysisStatus {

    OK(0), UNKNOWN_ERROR(999);

    private int errorCode;

    AnalysisStatus(int errorCode) {
        this.errorCode = errorCode;
    }

}

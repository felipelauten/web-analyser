package com.devlauten.webanalyzer.domain.data.entities;

import com.devlauten.webanalyzer.domain.data.entities.enums.ResponseItemType;

public class AnalysisItemFactory {

    public static AnalysisItem getAnalysisType(Object algorithmResult, ResponseItemType type) {
        if (algorithmResult.getClass() == Boolean.class) {
            return new AnalysisItemBoolean((Boolean) algorithmResult, type);
        } else if (algorithmResult.getClass() == Integer.class) {
            return new AnalysisItemInteger((Integer) algorithmResult, type);
        } else if (algorithmResult.getClass() == String.class) {
            return new AnalysisItemString((String) algorithmResult, type);
        }
        return null;
    }
}

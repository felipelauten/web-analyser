package com.devlauten.webanalyzer.rest.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class AnalysisResponse<Item> {
    Map<Item, AnalysisItem> analysisMap;
}

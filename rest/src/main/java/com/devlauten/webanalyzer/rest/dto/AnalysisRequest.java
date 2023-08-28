package com.devlauten.webanalyzer.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value(staticConstructor = "of")
@AllArgsConstructor
public class AnalysisRequest {
    String url;
}

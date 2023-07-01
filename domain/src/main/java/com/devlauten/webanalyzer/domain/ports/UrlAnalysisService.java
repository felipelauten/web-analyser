package com.devlauten.webanalyzer.domain.ports;

import com.devlauten.webanalyzer.domain.data.entities.AnalysisInput;
import com.devlauten.webanalyzer.domain.data.entities.AnalysisItem;
import com.devlauten.webanalyzer.domain.data.entities.enums.AnalysisStatus;
import com.devlauten.webanalyzer.domain.data.entities.enums.ResponseItemType;

import java.util.Map;

public interface UrlAnalysisService {

    Map<ResponseItemType, AnalysisItem<?>> analyseRemoteUrl(AnalysisInput input, String ip) throws Exception;

    Map<String, AnalysisStatus> linkHealthCheck(Long analysisId) throws Exception;
}

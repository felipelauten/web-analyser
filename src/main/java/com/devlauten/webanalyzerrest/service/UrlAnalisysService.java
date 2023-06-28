package com.devlauten.webanalyzerrest.service;

import com.devlauten.webanalyzerrest.model.enums.AnalysisStatus;
import com.devlauten.webanalyzerrest.model.AnalysisInput;
import com.devlauten.webanalyzerrest.model.AnalysisItem;
import com.devlauten.webanalyzerrest.model.enums.ResponseItemType;

import java.util.Map;

public interface UrlAnalisysService {

    Map<ResponseItemType, AnalysisItem<?>> analyseRemoteUrl(AnalysisInput input, String ip) throws Exception;

    Map<String, AnalysisStatus> linkHealthCheck(Long analysisId) throws Exception;
}

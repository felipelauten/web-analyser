package de.scout24.webanalyzerrest.service;

import de.scout24.webanalyzerrest.model.AnalysisInput;
import de.scout24.webanalyzerrest.model.AnalysisItem;
import de.scout24.webanalyzerrest.model.enums.ResponseItemType;

import java.util.Map;

public interface UrlAnalisysService {

    Map<ResponseItemType, AnalysisItem> analyseRemoteUrl(AnalysisInput input, String ip) throws Exception;
}

package de.scout24.webanalyzerrest.service;

import de.scout24.webanalyzerrest.model.AnalysisInput;
import de.scout24.webanalyzerrest.model.AnalysisOutput;

public interface UrlAnalisysService {

    AnalysisOutput analyseRemoteUrl(AnalysisInput input) throws Exception;
}

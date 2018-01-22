package de.scout24.webanalyzerrest.service;

import de.scout24.webanalyzerrest.model.AnalysisInput;
import de.scout24.webanalyzerrest.model.AnalysisOutput;
import de.scout24.webanalyzerrest.model.enums.AnalysisStatus;
import de.scout24.webanalyzerrest.repository.UrlAnalisysRestRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlAnalysisServiceImpl implements UrlAnalisysService {

    @Autowired
    private UrlAnalisysRestRepository repository;

    @Override
    public AnalysisOutput analyseRemoteUrl(AnalysisInput input) throws Exception {
        if (input == null || StringUtils.isEmpty(input.getUrl())) {
            throw new Exception("Invalid input object");
        }
        Optional<String> htmlContents = repository.getHtmlFromUrl(input.getUrl());

        if (!htmlContents.isPresent()) {
            return new AnalysisOutput(AnalysisStatus.UNKNOWN_ERROR);
        }

        String html = htmlContents.get();
        return new AnalysisOutput(AnalysisStatus.OK);
    }

    @SuppressWarnings("unused")
    public void setRepository(UrlAnalisysRestRepository repository) {
        this.repository = repository;
    }
}

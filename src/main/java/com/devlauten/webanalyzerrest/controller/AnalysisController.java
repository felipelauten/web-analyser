package com.devlauten.webanalyzerrest.controller;

import com.devlauten.webanalyzerrest.model.AnalysisInput;
import com.devlauten.webanalyzerrest.model.AnalysisItem;
import com.devlauten.webanalyzerrest.model.enums.AnalysisStatus;
import com.devlauten.webanalyzerrest.model.enums.ResponseItemType;
import com.devlauten.webanalyzerrest.service.UrlAnalisysService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/analysis")
@SuppressWarnings("unused")
public class AnalysisController {

    private static final Logger LOG = LoggerFactory.getLogger(AnalysisController.class.getName());

    @Autowired
    UrlAnalisysService service;

    @RequestMapping(value = "/analyze", method = RequestMethod.POST)
    public Map<ResponseItemType, AnalysisItem<?>> performAnalysisByURL(@RequestBody AnalysisInput input, HttpServletRequest request) {

        if (input == null || StringUtils.isEmpty(input.getUrl())) {
            LOG.warn("User sent empty URL");
            return new HashMap<>();
        }

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        try {
            return service.analyseRemoteUrl(input, ipAddress);
        } catch (Exception e) {
            LOG.error(String.format("Exception while analysing the URL [%s]", input.getUrl()), e);
            return new HashMap<>();
        }
    }

    @RequestMapping(value = "/url-check/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, AnalysisStatus> performURLAnalysisCheck(@PathVariable(value = "id") Long analysisId) throws Exception {
        if (Objects.isNull(analysisId) || Objects.equals(0L, analysisId)) {
            return Collections.emptyMap();
        }

        return service.linkHealthCheck(analysisId);
    }

    public void setService(UrlAnalisysService service) {
        this.service = service;
    }
}

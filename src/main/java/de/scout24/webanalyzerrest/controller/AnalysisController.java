package de.scout24.webanalyzerrest.controller;

import de.scout24.webanalyzerrest.model.AnalysisInput;
import de.scout24.webanalyzerrest.model.AnalysisItem;
import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import de.scout24.webanalyzerrest.service.UrlAnalisysService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/analysis")
@SuppressWarnings("unused")
public class AnalysisController {

    private static Logger LOG = LoggerFactory.getLogger(AnalysisController.class.getName());

    @Autowired
    UrlAnalisysService service;

    @RequestMapping(value = "/analyze", method = RequestMethod.POST)
    public @ResponseBody
    Map<ResponseItemType, AnalysisItem> performAnalysisByURL(@RequestBody AnalysisInput input, HttpServletRequest request) {

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

    public @ResponseBody
    @RequestMapping(value = "/url-check")
    String performURLAnalysisCheck(@RequestParam String url, @RequestParam String analysisId) {
        return "OK!";
    }

    public void setService(UrlAnalisysService service) {
        this.service = service;
    }
}

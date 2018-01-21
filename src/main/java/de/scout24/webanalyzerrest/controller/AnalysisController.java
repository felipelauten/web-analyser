package de.scout24.webanalyzerrest.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/analysis")
@SuppressWarnings("unused")
public class AnalysisController {

    private static Logger LOG = LoggerFactory.getLogger(AnalysisController.class.getName());

    @RequestMapping(value = "/hello")
    public @ResponseBody String helloWorld() {
        return "Hello World!";
    }

    @RequestMapping(value = "/analyze", method = RequestMethod.GET)
    public @ResponseBody
    String performAnalysisByURL() {
        String url = "https://www.facebook.com/";
        if (StringUtils.isEmpty(url)) {
            LOG.warn("User sent empty URL");
            return "Empty URL String";
        }
        return String.format("Analyzing URL [%s]", url);
    }
}

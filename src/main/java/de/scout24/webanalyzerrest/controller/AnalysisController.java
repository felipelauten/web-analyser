package de.scout24.webanalyzerrest.controller;

import de.scout24.webanalyzerrest.model.AnalysisInput;
import de.scout24.webanalyzerrest.model.AnalysisOutput;
import de.scout24.webanalyzerrest.model.enums.AnalysisStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/analysis")
@SuppressWarnings("unused")
public class AnalysisController {

    private static Logger LOG = LoggerFactory.getLogger(AnalysisController.class.getName());

    @RequestMapping(value = "/analyze", method = RequestMethod.POST)
    public @ResponseBody
    AnalysisOutput performAnalysisByURL(@RequestBody AnalysisInput input) {

        if (input == null || StringUtils.isEmpty(input.getUrl())) {
            LOG.warn("User sent empty URL");
            return new AnalysisOutput(AnalysisStatus.UNKNOWN_ERROR);
        }
        String url = input.getUrl();


        return new AnalysisOutput(AnalysisStatus.OK);
    }
}

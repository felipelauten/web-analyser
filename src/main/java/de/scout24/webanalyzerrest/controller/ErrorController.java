package de.scout24.webanalyzerrest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    public static final String ERROR_RESPONSE = "Error!";
    private Logger LOG = LoggerFactory.getLogger(this.getClass().getName());

    public @ResponseBody String errorConroller(Exception e) {
        LOG.error("Generic error", e);
        return ERROR_RESPONSE;
    }
}

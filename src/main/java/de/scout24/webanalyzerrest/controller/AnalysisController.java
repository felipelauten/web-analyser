package de.scout24.webanalyzerrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/analysis")
public class AnalysisController {

    @RequestMapping(value = "/")
    public @ResponseBody String helloWorld(Optional<String> name) {
        if (name.isPresent()) {
            return "Hello " + name + "!";
        }
        return "Hello World!";
    }
}

package com.devlauten.webanalyzerrest.controller;

import com.devlauten.webanalyzerrest.WebAnalyzerRestApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = WebAnalyzerRestApplication.class)
@AutoConfigureMockMvc
public class AnalysisControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAnaliseEndpoint() throws Exception {
        mockMvc.perform(get("/analyze"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
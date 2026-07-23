package com.devlauten.webanalyzer.rest.controller;

import com.devlauten.webanalyzer.domain.data.entities.AnalysisInput;
import com.devlauten.webanalyzer.domain.data.entities.AnalysisItemString;
import com.devlauten.webanalyzer.domain.data.entities.enums.AnalysisStatus;
import com.devlauten.webanalyzer.domain.data.entities.enums.ResponseItemType;
import com.devlauten.webanalyzer.domain.ports.UrlAnalysisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnalysisController.class)
class AnalysisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlAnalysisService urlAnalysisService;

    @Test
    void analyze_withValidUrl_returnsAnalysisResults() throws Exception {
        Map<ResponseItemType, com.devlauten.webanalyzer.domain.data.entities.AnalysisItemData<?>> results = new HashMap<>();
        results.put(ResponseItemType.PAGE_TITLE, new AnalysisItemString("Example", ResponseItemType.PAGE_TITLE));

        when(urlAnalysisService.analyseRemoteUrl(any(AnalysisInput.class), eq("127.0.0.1")))
                .thenReturn(results);

        mockMvc.perform(post("/analysis/analyze")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"https://example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.PAGE_TITLE.result").value("Example"));

        verify(urlAnalysisService).analyseRemoteUrl(any(AnalysisInput.class), eq("127.0.0.1"));
    }

    @Test
    void analyze_withEmptyUrl_returnsEmptyMap() throws Exception {
        mockMvc.perform(post("/analysis/analyze")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());

        verifyNoInteractions(urlAnalysisService);
    }

    @Test
    void analyze_whenServiceThrows_returnsEmptyMap() throws Exception {
        when(urlAnalysisService.analyseRemoteUrl(any(AnalysisInput.class), any()))
                .thenThrow(new RuntimeException("upstream failure"));

        mockMvc.perform(post("/analysis/analyze")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"https://example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void urlCheck_withValidId_returnsLinkStatuses() throws Exception {
        Map<String, AnalysisStatus> statuses = Map.of("https://example.com", AnalysisStatus.OK);

        when(urlAnalysisService.linkHealthCheck(42L)).thenReturn(statuses);

        mockMvc.perform(get("/analysis/url-check/42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['https://example.com']").value("OK"));
    }

    @Test
    void urlCheck_withZeroId_returnsEmptyMap() throws Exception {
        mockMvc.perform(get("/analysis/url-check/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());

        verifyNoInteractions(urlAnalysisService);
    }
}

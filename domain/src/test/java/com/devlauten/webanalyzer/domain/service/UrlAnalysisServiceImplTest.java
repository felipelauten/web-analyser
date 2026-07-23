package com.devlauten.webanalyzer.domain.service;

import com.devlauten.webanalyzer.domain.data.entities.AnalysisInput;
import com.devlauten.webanalyzer.domain.data.entities.enums.AnalysisStatus;
import com.devlauten.webanalyzer.domain.data.repository.AnalysisOutputRepository;
import com.devlauten.webanalyzer.domain.external.client.HtmlClient;
import com.devlauten.webanalyzer.domain.ports.UrlAnalysisService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UrlAnalysisServiceImplTest {

    @Mock
    private AnalysisOutputRepository outputRepository;

    @Mock
    private HtmlClient htmlClient;

    @InjectMocks
    private UrlAnalysisService service = new UrlAnalysisServiceImpl();

    @Test(expected = RuntimeException.class)
    public void linkHealthCheckShouldFailWithNullAnalysisId() throws Exception {
        // given
        Long nullLong = null;

        // when
        service.linkHealthCheck(nullLong);
    }

    @Test(expected = RuntimeException.class)
    public void linkHealthCheckShouldFailWithZeroAnalysisId() throws Exception {
        // given
        Long aLong = 0L;

        // when
        service.linkHealthCheck(aLong);
    }

    @Test
    public void linkHealthCheckShouldReturnEmptyMapForNoOutput() throws Exception {
        // given
        Mockito.when(outputRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        // when
        Map<String, AnalysisStatus> map = service.linkHealthCheck(1L);

        assertNotNull(map);
        assertEquals(Collections.emptyMap(), map);
    }

    @Test(expected = Exception.class)
    public void analyseRemoteUrlShouldFailWithEmptyUrl() throws Exception {
        service.analyseRemoteUrl(new AnalysisInput(), "127.0.0.1");
    }

    @Test
    public void analyseRemoteUrlShouldReturnEmptyMapWhenHtmlIsUnavailable() throws Exception {
        AnalysisInput input = new AnalysisInput();
        input.setUrl("https://example.com");

        Mockito.when(htmlClient.getHtmlFromUrl("https://example.com")).thenReturn(Optional.empty());

        Map<?, ?> result = service.analyseRemoteUrl(input, "127.0.0.1");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
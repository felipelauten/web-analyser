package com.devlauten.webanalyzer.domain.service;

import com.devlauten.webanalyzer.domain.data.entities.enums.AnalysisStatus;
import com.devlauten.webanalyzer.domain.data.repository.AnalysisOutputRepository;
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

@RunWith(MockitoJUnitRunner.class)
public class UrlAnalysisServiceImplTest {

    @Mock
    private AnalysisOutputRepository outputRepository;

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
}
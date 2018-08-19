package de.scout24.webanalyzerrest.service;

import de.scout24.webanalyzerrest.model.enums.AnalysisStatus;
import de.scout24.webanalyzerrest.repository.AnalysisOutputRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UrlAnalysisServiceImplTest {

    @Mock
    private AnalysisOutputRepository outputRepository;

    @InjectMocks
    private UrlAnalisysService service = new UrlAnalysisServiceImpl();

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
        Long aLong = new Long(0);

        // when
        service.linkHealthCheck(aLong);
    }

    @Test
    public void linkHealthCheckShouldReturnEmptyMapForNoOutput() throws Exception {
        // given
        Mockito.when(outputRepository.findOne(Mockito.anyLong())).thenReturn(null);

        // when
        Map<String, AnalysisStatus> map = service.linkHealthCheck(1L);

        assertNotNull(map);
        assertEquals(Collections.emptyMap(), map);
    }
}
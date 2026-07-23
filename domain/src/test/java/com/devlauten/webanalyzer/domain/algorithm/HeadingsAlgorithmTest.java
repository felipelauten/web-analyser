package com.devlauten.webanalyzer.domain.algorithm;

import com.devlauten.webanalyzer.domain.data.entities.AnalysisItemData;
import com.devlauten.webanalyzer.domain.data.entities.enums.ResponseItemType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HeadingsAlgorithmTest {

    private final HeadingsAlgorithm algorithm = new HeadingsAlgorithm();

    @Test
    public void execute_countsHeadingsByLevel() throws Exception {
        Document document = Jsoup.parse("""
                <html><body>
                    <h1>One</h1>
                    <h2>Two A</h2>
                    <h2>Two B</h2>
                    <h3>Three</h3>
                </body></html>
                """);

        AnalysisItemData<Map<String, Integer>> result = algorithm.execute(document);

        assertEquals(ResponseItemType.NUMBER_OF_HEADINGS, result.getItemType());
        assertEquals(Integer.valueOf(1), result.getResult().get("h1"));
        assertEquals(Integer.valueOf(2), result.getResult().get("h2"));
        assertEquals(Integer.valueOf(1), result.getResult().get("h3"));
        assertEquals(Integer.valueOf(0), result.getResult().get("h4"));
    }

    @Test
    public void execute_returnsZeroCountsForPageWithoutHeadings() throws Exception {
        Document document = Jsoup.parse("<html><body><p>No headings here</p></body></html>");

        AnalysisItemData<Map<String, Integer>> result = algorithm.execute(document);

        assertEquals(Integer.valueOf(0), result.getResult().get("h1"));
        assertEquals(Integer.valueOf(0), result.getResult().get("h6"));
    }
}

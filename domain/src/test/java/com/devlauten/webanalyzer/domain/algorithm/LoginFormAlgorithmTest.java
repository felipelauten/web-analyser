package com.devlauten.webanalyzer.domain.algorithm;

import com.devlauten.webanalyzer.domain.data.entities.AnalysisItemData;
import com.devlauten.webanalyzer.domain.data.entities.enums.ResponseItemType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginFormAlgorithmTest {

    private final LoginFormAlgorithm algorithm = new LoginFormAlgorithm();

    @Test
    public void execute_detectsLoginFormWithUsernameAndPassword() throws Exception {
        Document document = Jsoup.parse("""
                <html><body>
                    <form>
                        <input name="username" />
                        <input name="password" />
                    </form>
                </body></html>
                """);

        AnalysisItemData<Boolean> result = algorithm.execute(document);

        assertEquals(ResponseItemType.LOGIN_FORM, result.getItemType());
        assertTrue(result.getResult());
    }

    @Test
    public void execute_returnsFalseWhenPasswordFieldIsMissing() throws Exception {
        Document document = Jsoup.parse("""
                <html><body>
                    <form>
                        <input name="username" />
                    </form>
                </body></html>
                """);

        AnalysisItemData<Boolean> result = algorithm.execute(document);

        assertFalse(result.getResult());
    }

    @Test
    public void execute_returnsFalseWhenNoFormIsPresent() throws Exception {
        Document document = Jsoup.parse("<html><body><p>Hello</p></body></html>");

        AnalysisItemData<Boolean> result = algorithm.execute(document);

        assertEquals(ResponseItemType.LOGIN_FORM, result.getItemType());
        assertFalse(result.getResult());
    }
}

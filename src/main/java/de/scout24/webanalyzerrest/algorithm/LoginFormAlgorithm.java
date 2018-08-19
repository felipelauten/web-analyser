package de.scout24.webanalyzerrest.algorithm;

import de.scout24.webanalyzerrest.algorithm.exception.AlgoruthmException;
import de.scout24.webanalyzerrest.model.AnalysisItem;
import de.scout24.webanalyzerrest.model.AnalysisItemBoolean;
import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(LoginFormAlgorithm.ALGORITHM_NAME)
public class LoginFormAlgorithm implements Algorithm {

    public static final String ALGORITHM_NAME = "loginFormAlgorithm";

    @Override
    public AnalysisItem execute(Document dom) throws AlgoruthmException {
        Elements formTag = dom.select("form");
        if (formTag != null) {
            boolean loginFieldOk = false;
            boolean passwordFieldOk = false;

            Elements inputTags = dom.select("input");
            for (Element input : inputTags) {
                String inputName = input.attr("name");
                String inputId = input.attr("id");

                if (inputName.contains("login") || inputName.contains("user") ||
                        inputId.contains("login") || inputId.contains("user")) {
                    loginFieldOk = true;
                }

                if (inputName.contains("password") || inputName.contains("password")) {
                    passwordFieldOk = true;
                }
            }

            return new AnalysisItemBoolean(loginFieldOk && passwordFieldOk, ResponseItemType.LOGIN_FORM);
        }


        return null;
    }

    @Override
    public ResponseItemType getItemType() {
        return ResponseItemType.LOGIN_FORM;
    }
}

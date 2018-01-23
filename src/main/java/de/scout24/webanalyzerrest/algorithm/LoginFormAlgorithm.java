package de.scout24.webanalyzerrest.algorithm;

import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LoginFormAlgorithm implements Algorithm<Boolean> {

    @Override
    public Boolean execute(Document dom) {
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

            return loginFieldOk && passwordFieldOk;
        }


        return false;
    }

    @Override
    public ResponseItemType getItemType() {
        return ResponseItemType.LOGIN_FORM;
    }
}

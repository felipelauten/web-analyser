package de.scout24.webanalyzerrest.algorithm;

import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;

import java.util.Optional;

/**
 * Definition of algorithm used to analyse the HTML page
 *
 * @param <R> - Type of return of the algorithm
 * @see de.scout24.webanalyzerrest.model.enums.HtmlVersion (R)
 * @see de.scout24.webanalyzerrest.model.enums.ResponseItemType (I)
 * @see Document
 */
public interface Algorithm<R> {

    /**
     * Performs the analysis of the algorithm.
     *
     * @param dom to be analysed
     * @return R - result
     * @throws Exception if something bad happens
     */
    R execute(Document dom);

    /**
     * Common operation of the algorithms, use tag name to filter the dom tree and get its content.
     *
     * @return Optional containing the desired string or empty optional
     */
    default Optional<String> findByTagName(Element element, String tagName) {
        if (element.getAllElements().size() == 0) { // No sub-elements
            return Optional.empty();
        }
        if (element.getAllElements().size() > 1) {
            Elements tag = element.getElementsByTag(tagName);
            if (tag != null) {
                return Optional.of(tag.text());
            }
        }
        return Optional.empty();
    }

    /**
     * Utility method to log information about found tags by the algorithm (or anything else).
     *
     * @param logger  to be used
     * @param message to be displayed
     * @param args    used by the message
     */
    default void logInformation(Logger logger, String message, Object... args) {
        logger.info(String.format(message, args));
    }

    /**
     * Returns the ResponseItemType handled by this algorithm.
     *
     * @return <code>ResponseItemType</code>
     */
    ResponseItemType getItemType();
}

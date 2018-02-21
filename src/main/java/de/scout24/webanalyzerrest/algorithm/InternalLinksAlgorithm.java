package de.scout24.webanalyzerrest.algorithm;

import de.scout24.webanalyzerrest.Counter;
import de.scout24.webanalyzerrest.algorithm.exception.AlgoruthmException;
import de.scout24.webanalyzerrest.model.AdditionalInformation;
import de.scout24.webanalyzerrest.model.AnalysisItem;
import de.scout24.webanalyzerrest.model.AnalysisItemInteger;
import de.scout24.webanalyzerrest.model.enums.AdditionalInformationType;
import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier(InternalLinksAlgorithm.ALGORITHM_NAME)
public class InternalLinksAlgorithm extends PageLinksAbstractAlgorithm implements Algorithm {

    public static final String ALGORITHM_NAME = "internalLinksAlgorithm";
    private static Logger LOG = LoggerFactory.getLogger(InternalLinksAlgorithm.class);

    @Override
    public AnalysisItem execute(Document dom) throws AlgoruthmException {
        List<Element> tags = dom.getElementsByTag(LINK_TAG);
        List<String> internalTagLinks = new ArrayList<>();
        Counter internalLinksCount = new Counter();

        LOG.info(String.format("Found %s links in the page.", tags.size()));
        for (Element tag : tags) {
            String tagLink = tag.attr(HREF_ATTR);
            if (isValidLink(tagLink, dom.baseUri())) {
                internalLinksCount.increment();
                internalTagLinks.add(tagLink);
            }
        }
        LOG.info(String.format("Found %s external links in the page:", internalLinksCount.getCount()));
        internalTagLinks.stream().forEach(link -> LOG.info(link));

        AnalysisItemInteger itemInteger = new AnalysisItemInteger(internalLinksCount.getCount(),
                ResponseItemType.INTERNAL_LINKS);
        AdditionalInformation additionalInformation = new AdditionalInformation("Internal Links Check",
                "additionalInfo", AdditionalInformationType.INTERNAL_LINKS_ANALYSIS);
        itemInteger.setAdditionalInformation(additionalInformation);
        return itemInteger;
    }

    boolean isValidLink(String link, String baseUrl) {
        if (StringUtils.isEmpty(link) || StringUtils.isEmpty(baseUrl)) {
            return false;
        }
        if (link.startsWith(ANCHOR_LINK) || link.startsWith(JAVASCRIPT_PREFIX)) {
            return true;
        }
        return link.contains(baseUrl) || link.startsWith(RELATIVE_LINK);
    }

    @Override
    public ResponseItemType getItemType() {
        return ResponseItemType.INTERNAL_LINKS;
    }
}

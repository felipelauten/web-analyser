package com.devlauten.webanalyzer.domain.algorithm;

import com.devlauten.webanalyzer.domain.data.entities.AdditionalInformation;
import com.devlauten.webanalyzer.domain.data.entities.AnalysisItemData;
import com.devlauten.webanalyzer.domain.data.entities.AnalysisItemDataInteger;
import com.devlauten.webanalyzer.domain.data.entities.enums.AdditionalInformationType;
import com.devlauten.webanalyzer.domain.data.entities.enums.ResponseItemType;
import com.devlauten.webanalyzer.domain.algorithm.exception.AlgorithmException;
import com.devlauten.webanalyzer.domain.util.Counter;
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
@Qualifier(ExternalLinksAlgorithm.ALGORITHM_NAME)
public class ExternalLinksAlgorithm extends PageLinksAbstractAlgorithm implements Algorithm<Integer> {

    public static final String ALGORITHM_NAME = "internalLinksAlgorithm";

    private static Logger LOG = LoggerFactory.getLogger(ExternalLinksAlgorithm.class);

    @Override
    public AnalysisItemData<Integer> execute(Document dom) throws AlgorithmException {
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

        AnalysisItemData<Integer> item = new AnalysisItemDataInteger(internalLinksCount.getCount(),
                ResponseItemType.EXTERNAL_LINKS);
        AdditionalInformation additionalInformation = new AdditionalInformation("Internal Links Check",
                "additionalInfo", AdditionalInformationType.INTERNAL_LINKS_ANALYSIS);
        item.setAdditionalInformation(additionalInformation);

        return item;
    }

    boolean isValidLink(String link, String baseUrl) {
        if (StringUtils.isEmpty(link) || StringUtils.isEmpty(baseUrl)) {
            return false;
        }
        if (link.startsWith(ANCHOR_LINK) || link.startsWith(JAVASCRIPT_PREFIX)) {
            return false;
        }
        return !link.contains(baseUrl) && !link.startsWith(RELATIVE_LINK);
    }

    @Override
    public ResponseItemType getItemType() {
        return ResponseItemType.EXTERNAL_LINKS;
    }
}

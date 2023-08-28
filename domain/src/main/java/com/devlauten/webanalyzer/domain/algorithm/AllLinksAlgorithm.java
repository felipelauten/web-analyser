package com.devlauten.webanalyzer.domain.algorithm;

import com.devlauten.webanalyzer.domain.data.entities.AnalysisItemData;
import com.devlauten.webanalyzer.domain.data.entities.AnalysisItemDataMap;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Qualifier(AllLinksAlgorithm.ALGORITHM_NAME)
public class AllLinksAlgorithm extends PageLinksAbstractAlgorithm implements Algorithm<Map<String, Integer>> {

    public static final String ALGORITHM_NAME = "allLinksAlgorithm";
    private static Logger LOG = LoggerFactory.getLogger(InternalLinksAlgorithm.class);

    @Override
    public AnalysisItemData<Map<String, Integer>> execute(Document dom) throws AlgorithmException {
        List<Element> tags = dom.getElementsByTag(LINK_TAG);
        Counter linksCount = new Counter();
        Map<String, Integer> allLinksCount = new HashMap<>();

        LOG.info(String.format("Found %s links in the page.", tags.size()));
        for (Element tag : tags) {
            String tagLink = tag.attr(HREF_ATTR);
            if (isValidLink(tagLink, dom.baseUri())) {
                Integer linkCount = allLinksCount.get(tagLink);
                linksCount.increment();
                if (linkCount != null) {
                    allLinksCount.put(tagLink, linkCount++);
                } else {
                    allLinksCount.put(tagLink, 1);
                }
            }
        }
        LOG.info(String.format("Found %s external links in the page:", linksCount.getCount()));

        return new AnalysisItemDataMap(allLinksCount, ResponseItemType.ALL_LINKS_MAP);
    }

    @Override
    public ResponseItemType getItemType() {
        return ResponseItemType.ALL_LINKS_MAP;
    }

    @Override
    boolean isValidLink(String link, String baseUrl) {
        if (StringUtils.isEmpty(link) || StringUtils.isEmpty(baseUrl)) {
            return false;
        }
        if (link.startsWith(ANCHOR_LINK) || link.startsWith(JAVASCRIPT_PREFIX)) {
            return false;
        }
        return !link.contains(baseUrl) && !link.startsWith(RELATIVE_LINK);
    }
}

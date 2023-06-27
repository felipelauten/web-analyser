package de.scout24.webanalyzerrest.algorithm;

import java.util.Map;

public abstract class PageLinksAbstractAlgorithm {

    public static final String LINK_TAG = "a";
    public static final String HREF_ATTR = "href";
    public static final String RELATIVE_LINK = "/";
    public static final String ANCHOR_LINK = "#";
    public static final String JAVASCRIPT_PREFIX = "javascript:";

    abstract boolean isValidLink(String link, String baseUri);
}

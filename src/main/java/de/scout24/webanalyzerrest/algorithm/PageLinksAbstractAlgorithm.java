package de.scout24.webanalyzerrest.algorithm;

public abstract class PageLinksAbstractAlgorithm implements Algorithm<Integer> {

    public static final String LINK_TAG = "a";
    public static final String HREF_ATTR = "href";
    public static final String RELATIVE_LINK = "/";
    public static final String ANCHOR_LINK = "#";
    public static final String JAVASCRIPT_PREFIX = "javascript:";

    abstract boolean isValidLink(String link, String baseUri);
}

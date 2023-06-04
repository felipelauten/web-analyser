package de.scout24.webanalyzerrest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class UrlAnalisysRestRepository {

    public static final String LOCATION = "Location";
    private static final int MAX_DEPTH = 3;
    @Autowired
    private RestTemplate template;

    /**
     * Get the contents of a specified URL.
     *
     * @param url
     * @return
     */
    public Optional<String> getHtmlFromUrl(final String url) throws Exception {
        return getHtmlFromUrl(url, 0);
    }

    private Optional<String> getHtmlFromUrl(final String url, int depth) throws Exception {
        if (depth > MAX_DEPTH)
            throw new Exception("Too many redirects, the maximum available redirect rate is " + MAX_DEPTH);

        ResponseEntity<String> entity = template.getForEntity(url, String.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(entity.getBody());
        }
        if (entity.getStatusCode().is3xxRedirection()) {
            return getHtmlFromUrl(url, depth + 1);
        }
        if (entity.getStatusCode().is4xxClientError()) {
            throw new Exception("The requested page was not found");
        }
        if (entity.getStatusCode().is5xxServerError()) {
            throw new Exception("The requested page returned an 500 error");
        }
        return Optional.empty();
    }

    public boolean checkUrlConnectivity(final String url) {
        ResponseEntity<String> entity;
        String workUrl = url;
        do {
            entity = template.getForEntity(workUrl, String.class, Optional.empty());
            if (entity.getStatusCode().is3xxRedirection()) {
                workUrl = entity.getHeaders().get(LOCATION).get(0);
            }
        } while (entity.getStatusCode().is3xxRedirection());
        if (entity.getStatusCode().is4xxClientError()) {
            return false;
        }
        if (entity.getStatusCode().is5xxServerError()) {
            return false;
        }
        return true;
    }

    public void setTemplate(RestTemplate template) {
        this.template = template;
    }
}

package de.scout24.webanalyzerrest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class UrlAnalisysRestRepository {

    @Autowired
    private RestTemplate template;

    /**
     * Get the contents of a specified URL.
     *
     * @param url
     * @return
     */
    public Optional<String> getHtmlFromUrl(String url) {
        ResponseEntity<String> entity = template.getForEntity(url, String.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            return Optional.of(entity.getBody());
        }
        return Optional.empty();
    }

    public void setTemplate(RestTemplate template) {
        this.template = template;
    }
}

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
    public Optional<String> getHtmlFromUrl(String url) throws Exception {
        ResponseEntity<String> entity = template.getForEntity(url, String.class);

        do {
            if (entity.getStatusCode().is4xxClientError()) {
                throw new Exception("The requested page was not found");
            }
            if (entity.getStatusCode().is5xxServerError()) {
                throw new Exception("The requested page returned an 500 error");
            }
            if (entity.getStatusCode().is2xxSuccessful()) {
                return Optional.of(entity.getBody());
            }
        } while (entity.getStatusCode().is3xxRedirection());
        return Optional.empty();
    }

    public void setTemplate(RestTemplate template) {
        this.template = template;
    }
}

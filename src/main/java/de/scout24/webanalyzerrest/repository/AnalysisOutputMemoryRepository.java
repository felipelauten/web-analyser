package de.scout24.webanalyzerrest.repository;

import de.scout24.webanalyzerrest.model.AnalysisOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Primary
@Qualifier("analysisOutputMemoryRepository")
public class AnalysisOutputMemoryRepository {

    private Map<String, AnalysisOutput> analysisOutputById;

    public AnalysisOutputMemoryRepository() {
        analysisOutputById = new HashMap<>();
    }

    public synchronized AnalysisOutput save(AnalysisOutput item, String ip) {
        AnalysisOutput output = analysisOutputById.get(ip);
        if (item != null) {
            analysisOutputById.put(ip, output);
        }
        return analysisOutputById.get(ip);
    }

    public Optional<AnalysisOutput> getByIp(String ip) {
        AnalysisOutput item = analysisOutputById.get(ip);
        if (item == null) {
            return Optional.empty();
        }
        return Optional.of(item);
    }
}

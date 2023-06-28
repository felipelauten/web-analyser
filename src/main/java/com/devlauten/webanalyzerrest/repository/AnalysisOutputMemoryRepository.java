package com.devlauten.webanalyzerrest.repository;

import com.devlauten.webanalyzerrest.util.Counter;
import com.devlauten.webanalyzerrest.model.AnalysisOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Primary
@Qualifier("analysisOutputMemoryRepository")
public class AnalysisOutputMemoryRepository {

    private Map<String, List<AnalysisOutput>> analysisOutputById;
    private Counter idCounter;

    public AnalysisOutputMemoryRepository() {
        analysisOutputById = new HashMap<>();
        idCounter = new Counter();
    }

    public synchronized List<AnalysisOutput> save(AnalysisOutput item, String ip) {
        List<AnalysisOutput> outputList = analysisOutputById.get(ip);
        if (outputList != null) {
            outputList = new ArrayList<>(outputList); // To ensure that the list is editable
            Optional<AnalysisOutput> analysisOutput = outputList.stream()
                    .filter(o -> o.getId().equals(item.getId()))
                    .findFirst();
            if (analysisOutput.isPresent()) {
                AnalysisOutput output = analysisOutput.get();
                output.setId((long) idCounter.incrementAndGet());
            } else {
                item.setId((long) idCounter.incrementAndGet());
                outputList.add(item);
            }
            analysisOutputById.put(ip, outputList);
        } else {
            item.setId((long) idCounter.incrementAndGet());
            analysisOutputById.put(ip, Arrays.asList(item));
        }
        return analysisOutputById.get(ip);
    }

    public Optional<List<AnalysisOutput>> getByIp(String ip) {
        List<AnalysisOutput> item = analysisOutputById.get(ip);
        if (item == null) {
            return Optional.empty();
        }
        return Optional.of(item);
    }
}

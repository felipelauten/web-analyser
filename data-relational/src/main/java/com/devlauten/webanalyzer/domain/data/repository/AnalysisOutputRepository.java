package com.devlauten.webanalyzer.domain.data.repository;

import com.devlauten.webanalyzer.domain.data.entities.AnalysisOutput;
import org.springframework.data.repository.CrudRepository;

public interface AnalysisOutputRepository extends CrudRepository<AnalysisOutput, Long> {

    // Just normal CRUD, Spring generates the methods

}

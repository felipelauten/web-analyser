package com.devlauten.webanalyzer.data.repository;

import com.devlauten.webanalyzer.data.entities.AnalysisOutput;
import org.springframework.data.repository.CrudRepository;

public interface AnalysisOutputRepository extends CrudRepository<AnalysisOutput, Long> {

    // Just normal CRUD, Spring generates the methods

}

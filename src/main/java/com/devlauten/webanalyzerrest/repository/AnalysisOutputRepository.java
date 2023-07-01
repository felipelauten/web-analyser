package com.devlauten.webanalyzerrest.repository;

import com.devlauten.webanalyzerrest.model.AnalysisOutput;
import org.springframework.data.repository.CrudRepository;

public interface AnalysisOutputRepository extends CrudRepository<AnalysisOutput, Long> {

    // Just normal CRUD, Spring generates the methods

}

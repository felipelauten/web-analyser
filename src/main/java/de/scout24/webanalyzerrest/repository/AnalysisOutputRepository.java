package de.scout24.webanalyzerrest.repository;

import de.scout24.webanalyzerrest.model.AnalysisOutput;
import org.springframework.data.repository.CrudRepository;

public interface AnalysisOutputRepository extends CrudRepository<AnalysisOutput, String> {

    // Just normal CRUD, Spring generates the methods

}

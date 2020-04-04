package org.uzh.ase.candidates.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.uzh.ase.candidates.model.Candidates;

import java.util.List;

public interface CandidatesRepository extends MongoRepository<Candidates, String> {
    List<Candidates> findByMovie1(String movie1);
}

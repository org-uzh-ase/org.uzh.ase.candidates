package org.uzh.ase.candidates.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.uzh.ase.candidates.model.Candidate;

public interface CandidateRepository extends MongoRepository<Candidate, String> {
    Candidate findByid(String id);
    List<Candidate> findBygenre(String genre);
}

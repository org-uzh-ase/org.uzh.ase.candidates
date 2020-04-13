package org.uzh.ase.candidates.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.uzh.ase.candidates.model.Candidate;

public interface CandidateRepository extends MongoRepository<Candidate, String> {
    Candidate findByid(String id);
}

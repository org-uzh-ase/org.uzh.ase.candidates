package org.uzh.ase.candidates.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.uzh.ase.candidates.model.Candidate;

/**
 * Interface allowing search over a MongoDB.
 * 
 * Used to define search methods over a MongoDB, and how the data in the MongoDB
 * should be mapped to a Java object an vice versa.
 */
public interface CandidateRepository extends MongoRepository<Candidate, String> {
    Candidate findByid(String id);
    List<Candidate> findBygenre(String genre);
}

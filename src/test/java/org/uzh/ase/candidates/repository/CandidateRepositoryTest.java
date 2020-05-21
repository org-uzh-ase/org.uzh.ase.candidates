package org.uzh.ase.candidates.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.uzh.ase.candidates.model.Candidate;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class CandidateRepositoryTest{

    @Autowired 
    CandidateRepository repository;

    private Candidate defaultCandidate = new Candidate();
    

    @BeforeEach
    public void addTestingData() {
        repository.save(defaultCandidate);
    }

    @AfterEach
    public void deleteTestingData() {
        repository.delete(defaultCandidate);
    }

    /**
     * Verify that saved movie is equal to one that was retrieved.
     * Verify that findByid() method returns movie with requested ID.
     */
    @Test
    public void testFindByid(){
        Candidate retrievedCandidate = repository.findById(defaultCandidate.getId()).get();

        assertTrue(retrievedCandidate.equals(defaultCandidate), "Candidate retrieved from the DB does not match the candidate that was inserted.");
    }

    /**
     * Verify that searching for movies by existing genres returns non-empty list
     * and each of the movies returned has the same genre.
     */
    @Test
    public void testFindBygenre(){
        List<Candidate> retrievedCandidates = repository.findBygenre(defaultCandidate.getGenre());

        assertFalse(retrievedCandidates.isEmpty());

        for (Candidate candidate : retrievedCandidates) {
            assertTrue(candidate.getGenre().equals(defaultCandidate.getGenre()), "Candidate retrieved from the DB does not match the candidate that was inserted.");
        }
    }
}

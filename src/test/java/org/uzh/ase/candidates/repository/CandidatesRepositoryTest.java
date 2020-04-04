package org.uzh.ase.candidates.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.uzh.ase.candidates.model.Candidates;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class CandidatesRepositoryTest {

    @Test
    public void testRepository(@Autowired CandidatesRepository repository){
        repository.deleteAll();

        repository.save(new Candidates("movie1", "movie2", "movie3"));
        List<Candidates> candidatesList = repository.findByMovie1("movie1");
        assertEquals(1, candidatesList.size());
        Candidates candidate = candidatesList.get(0);
        assertEquals("movie1", candidate.getMovie1());
        assertEquals("movie2", candidate.getMovie2());
        assertEquals("movie3", candidate.getMovie3());
    }
}

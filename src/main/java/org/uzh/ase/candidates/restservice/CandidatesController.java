package org.uzh.ase.candidates.restservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.uzh.ase.candidates.model.Candidate;

@RestController
public class CandidatesController {

    @GetMapping("/api/candidates")
    public List<Candidate> getCandidates(
        @RequestParam(value = "movie_id") String movieId, 
        @RequestParam(value = "level", defaultValue = "1") int level
        ) {
        return Arrays.asList(new Candidate(), new Candidate(), new Candidate(), new Candidate());
    }
}

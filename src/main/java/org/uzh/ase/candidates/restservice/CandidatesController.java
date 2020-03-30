package org.uzh.ase.candidates.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.uzh.ase.candidates.model.Answer;

@RestController
public class CandidatesController {

    @GetMapping("/api/candidates")
    public Answer getCandidates(@RequestParam(value = "movie_id") String movie_id, @RequestParam(value = "level", defaultValue = "1") int level) {
        return new Answer();
    }
}

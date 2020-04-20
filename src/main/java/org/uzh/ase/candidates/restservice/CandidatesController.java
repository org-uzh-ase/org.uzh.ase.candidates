package org.uzh.ase.candidates.restservice;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.uzh.ase.candidates.model.Candidate;
import org.uzh.ase.candidates.repository.CandidateRepository;

@RestController
public class CandidatesController {


	@Autowired
	private CandidateRepository repository;

    @GetMapping("/api/candidates")
    public List<Candidate> getCandidates(
        @RequestParam(value = "movie_id") String movieId, 
        @RequestParam(value = "level", defaultValue = "1") int level
        ) {
        Optional<Candidate> requestedMovie = repository.findById(movieId);

        if (requestedMovie.isPresent()){
            List<Candidate> candidates = new LinkedList<Candidate>();
            candidates.add(requestedMovie.get());
            List<Candidate> possibleCandidates;

            if (level == 1) {
                possibleCandidates = repository.findAll();                
            } else {
                possibleCandidates = repository.findBygenre(requestedMovie.get().getGenre());
            }
            possibleCandidates.remove(requestedMovie.get());

            if (possibleCandidates.size() < 3) { // check whether there are at least 3 candidates, otherwise request would get into a loop
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Number of candidates for the requested genre \"%s\" is less than 3.", requestedMovie.get().getGenre()));
            }

            for (int i : generateRandomIndices(possibleCandidates.size())) {
                candidates.add(possibleCandidates.get(i));
            }
            return candidates;

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Couldn't find movie with ID %s", movieId));
        }

    }

    private int[] generateRandomIndices(int numOfCandidates) {
        
        int[] randomIndices = new int[3];
        
        do { // Generate unique random indices
            randomIndices[0] = ThreadLocalRandom.current().nextInt(0, numOfCandidates); 
            randomIndices[1] = ThreadLocalRandom.current().nextInt(0, numOfCandidates); 
            randomIndices[2] = ThreadLocalRandom.current().nextInt(0, numOfCandidates); 
            
        } while ( //Keep generating until the indicies are NOT identical
            randomIndices[0] == randomIndices[1] ||
            randomIndices[1] == randomIndices[2] ||
            randomIndices[0] == randomIndices[2]);

        return randomIndices;
    }
}

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

/**
 * Controller responsible for handling API requests.
 */
@RestController
public class CandidatesController {

	@Autowired
	private CandidateRepository repository;

    /**
     * Method invoked for requests with "/api/candidates" path.
     * 
     * Extracts a requested movie from a DB, and finds 3 additional random movies,
     * depending on the difficulty. HTTP response is 404 if requested movie
     * cannot be found in the DB, OR we are not able to find more than 3
     * movies with similar genres in case of difficulty != 1.
     * @param movieId Requested movie IMDB ID.
     * @param level Requested level of difficulty; 1 for random movies, 
     * any other int for random movies with matching genres.
     * @return HTTP response filled with 3 random movies and a requested movie.
     */
    @GetMapping("/api/candidates")
    public List<Candidate> getCandidates(
        @RequestParam(value = "movie_id") String movieId, 
        @RequestParam(value = "level", defaultValue = "1") int level
        ) {
        Optional<Candidate> requestedMovie = repository.findById(movieId);

        if (requestedMovie.isPresent()){
            List<Candidate> candidates = new LinkedList<>();
            candidates.add(requestedMovie.get());
            List<Candidate> possibleCandidates;

            if (level == 1) {
                possibleCandidates = repository.findAll();                
            } else {
                possibleCandidates = repository.findBygenre(requestedMovie.get().getGenre());
            }
            possibleCandidates.remove(requestedMovie.get());

            if (possibleCandidates.size() < 3) { // check whether there are at least 3 candidates, otherwise request would get into a loop
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Number of candidates for the requested genre \"%s\" is less than 3.", requestedMovie.get().getGenre()));
            }

            for (int i : generateRandomIndices(possibleCandidates.size())) {
                candidates.add(possibleCandidates.get(i));
            }
            return candidates;

        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Couldn't find movie with ID %s", movieId));
        }

    }

    /**
     * Generates 3 random and unique numbers from range [0, numOfCandidates]
     * @param numOfCandidates Maximum range
     * @return Int array of size 3, with random numbers from range [0, numOfCandidates]
     */
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

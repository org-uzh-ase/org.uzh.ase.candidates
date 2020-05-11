package org.uzh.ase.candidates.restservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.uzh.ase.candidates.model.Candidate;
import org.uzh.ase.candidates.repository.CandidateRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CandidateControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    CandidateRepository repository;

    private Candidate defaultCandidate = new Candidate();

    private ObjectMapper objectMapper = new ObjectMapper();

    Logger log = LoggerFactory.getLogger(CandidateControllerTest.class);

    @BeforeEach
    public void addTestingData() {
        repository.save(defaultCandidate);
    }

    @AfterEach
    public void deleteTestingData() {
        repository.delete(defaultCandidate);
    }

   
    /**
     * Verify that an API returns correct number of responses
     * and that it returns the required movie.
     * @throws Exception HTTP response does not contain movie data
     */
    @Test
    public void testCandidatesAPI() throws Exception {
        String defaultCandidateID = defaultCandidate.getId();
        String urlPath = String.format("/api/candidates?movie_id=%s", defaultCandidateID);
        String httpResponse = this.restTemplate.getForObject("http://localhost:" + port + urlPath, String.class);
        List<Candidate> response = objectMapper.readValue(httpResponse, new TypeReference<List<Candidate>>(){});

        log.debug("Response for \"" + urlPath + "\" is:\"" + httpResponse + "\"");

        assertTrue(response.size() == 4);

        assertTrue(new HashSet<Candidate>(response).size() == 4);

        assertThat(response).contains(new Candidate());
    }

    /**
     * Veryfi that difficulty parameter returns movies with
     * similar genres.
     * @throws Exception HTTP response does not contain movie data
     */
    @Test
    public void testCandidatesDifficulty() throws Exception {
        String urlPath = String.format("/api/candidates?movie_id=%s&level=2", "1002540"); // movie that has genre a documentary
        String httpResponse = this.restTemplate.getForObject("http://localhost:" + port + urlPath, String.class);
        List<Candidate> response = objectMapper.readValue(httpResponse, new TypeReference<List<Candidate>>(){});

        log.debug("Response for \"" + urlPath + "\" is:\"" + httpResponse + "\"");

        for (Candidate candidate : response) {
            assertTrue(candidate.getGenre().equals("Documentary"));            
        }
    }

    /**
     * Verify that 404 response is returned if a requested movie does not exist.
     * @throws Exception HTTP response does not contain failure response.
     */
    @Test
    public void testCandidatesNonExistentMovie() throws Exception {
        String nonExistingID = "-1"; // I hope that this ID will not actually exist
        String urlPath = String.format("/api/candidates?movie_id=%s", nonExistingID);
        String httpResponse = this.restTemplate.getForObject("http://localhost:" + port + urlPath, String.class);
        Map<String, String> response = objectMapper.readValue(httpResponse, new TypeReference<Map<String,String>>(){});
        
        log.debug("Response for \"" + urlPath + "\" is:\"" + httpResponse + "\"");
        
        assertTrue(response.get("status").equals("404"));
    }

    /**
     * Verify that 404 response is returned if less than 4 movies
     * with the same genre exist.
     * @throws Exception
     */
    @Test
    public void testCandidatesLessThanThreeValidCandidates() throws Exception {
        Candidate c1 = new Candidate("1", "https::/something", "rareGenre", "sometitle");
        Candidate c2 = new Candidate("1", "https::/something", "rareGenre", "sometitle");
        Candidate c3 = new Candidate("1", "https::/something", "rareGenre", "sometitle");
        repository.save(c1);
        repository.save(c2);
        repository.save(c3);

        String urlPath = "/api/candidates?movie_id=1&level=4";
        String httpResponse = this.restTemplate.getForObject("http://localhost:" + port + urlPath, String.class);

        Map<String, String> response = objectMapper.readValue(httpResponse, new TypeReference<Map<String,String>>(){});
        
        log.debug("Response for \"" + urlPath + "\" is:\"" + httpResponse + "\"");
        
        assertTrue(response.get("status").equals("404"));
        
        repository.delete(c1);
        repository.delete(c2);
        repository.delete(c3);
    }
}

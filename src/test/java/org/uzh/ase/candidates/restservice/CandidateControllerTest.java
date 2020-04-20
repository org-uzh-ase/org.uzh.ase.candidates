package org.uzh.ase.candidates.restservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.uzh.ase.candidates.model.Candidate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CandidateControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    Logger log = LoggerFactory.getLogger(CandidateControllerTest.class);

    @Test
    public void testCandidatesAPI() throws Exception {
        String defaultCandidateID = new Candidate().getId();
        String urlPath = String.format("/api/candidates?movie_id=%s", defaultCandidateID);
        String response = this.restTemplate.getForObject("http://localhost:" + port + urlPath, String.class);
        
        log.debug("Response for \"" + urlPath + "\" is:\"" + response + "\"");

        assertThat(response).containsSequence(defaultCandidateID);

    }
}

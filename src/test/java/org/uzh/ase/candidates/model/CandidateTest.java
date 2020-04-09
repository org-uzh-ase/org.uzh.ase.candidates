package org.uzh.ase.candidates.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CandidateTest {

    private final String defaultID = "42";
    private final String defaultPosterURL = "https://images-na.ssl-images-amazon.com/images/M/MV5BMDU2ZWJlMjktMTRhMy00ZTA5LWEzNDgtYmNmZTEwZTViZWJkXkEyXkFqcGdeQXVyNDQ2OTk4MzI@._V1_UX182_CR0,0,182,268_AL_.jpg";

    @Test
    public void testNoArgConstructor(){
        Candidate candidate = new Candidate();
        assertEquals(defaultID, candidate.getId(), "Expected default value for ID is different.");
        assertEquals(defaultPosterURL, candidate.getPosterURL(), "Expected default value for Poster URL is different.");
    }

    @Test
    public void testArgConstructor(){
        String id = "5678";
        String posterUrl = "https://reddit.co./some.jpg";
        Candidate candidate = new Candidate(id, posterUrl);
        assertEquals(id, candidate.getId(), "Expected value for ID used in constructor is different.");
        assertEquals(posterUrl, candidate.getPosterURL(), "Expected value for Poster URL used in constructor is different.");
    }
}

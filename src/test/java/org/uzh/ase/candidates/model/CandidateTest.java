package org.uzh.ase.candidates.model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testEquals() {
        Candidate c = new Candidate();

        assertFalse(c.equals(null));
        assertFalse(c.equals(new Object()));
        assertFalse(c.equals(new Candidate("Different ID", "Different poster URL")));
        assertFalse(c.equals(new Candidate(defaultID, "Different poster URL")));
        assertFalse(c.equals(new Candidate("Different ID", defaultPosterURL)));

        assertTrue(c.equals(c));
        assertTrue(c.equals(new Candidate(defaultID, defaultPosterURL)));
    }

    @Test
    public void testHashCode() {
        Candidate c = new Candidate();
        assertEquals(c.hashCode(), new Candidate().hashCode());
        assertEquals(c.hashCode(), new Candidate(defaultID, defaultPosterURL).hashCode());

        assertNotEquals(c.hashCode(), new Candidate("Different ID", defaultPosterURL).hashCode());
        assertNotEquals(c.hashCode(), new Candidate(defaultID, "Different poster URL").hashCode());
        assertNotEquals(c.hashCode(), new Candidate("Different ID", "Different poster URL").hashCode());
    }
}

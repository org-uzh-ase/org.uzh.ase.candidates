package org.uzh.ase.candidates.model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CandidateTest {

    private final String defaultID = "42";
    private final String defaultPosterURL = "https://images-na.ssl-images-amazon.com/images/M/MV5BMDU2ZWJlMjktMTRhMy00ZTA5LWEzNDgtYmNmZTEwZTViZWJkXkEyXkFqcGdeQXVyNDQ2OTk4MzI@._V1_UX182_CR0,0,182,268_AL_.jpg";
    private final String defaultGenre = "Documentary";
    private final String defaultTitle = "Title of the Movie";

    /**
     * Verify that constructor uses the expected default data.
     * id = "42"
     * poster = "https://images-na.ssl-images-amazon.com/images/M/MV5BMDU2ZWJlMjktMTRhMy00ZTA5LWEzNDgtYmNmZTEwZTViZWJkXkEyXkFqcGdeQXVyNDQ2OTk4MzI@._V1_UX182_CR0,0,182,268_AL_.jpg"
     * genre = "Documentary"
     * title = "Title of the Movie"
     */
    @Test
    public void testNoArgConstructor(){
        Candidate candidate = new Candidate();
        assertEquals(defaultID, candidate.getId(), "Expected default value for ID is different.");
        assertEquals(defaultPosterURL, candidate.getPoster(), "Expected default value for Poster URL is different.");
        assertEquals(defaultGenre, candidate.getGenre(), "Expected default value for genre is different.");
    }

    /**
     * Verify that a candidate built with custom arguments
     * contains those arguments.
     */
    @Test
    public void testArgConstructor(){
        // These values have to be different from the default ones to avoid, accidentally passing the tests
        String id = "5678";
        String posterUrl = "https://reddit.co./some.jpg";
        String genre = "Apple juice";
        String title = "Tralala";

        Candidate candidate = new Candidate(id, posterUrl, genre, title);
        assertEquals(id, candidate.getId(), "Expected value for ID used in constructor is different.");
        assertEquals(posterUrl, candidate.getPoster(), "Expected value for Poster URL used in constructor is different.");
        assertEquals(genre, candidate.getGenre(), "Expected value for Genre used in constructor is different.");
        assertEquals(title, candidate.getTitle(), "Expected value for Title used in constructor is different.");
    }

    /**
     * Verify that Candidate equals() method evaluates to true, only if all
     * of the fields (id, poster, genre, title) hold identical values.
     */
    @Test
    public void testEquals() { 
        Candidate c = new Candidate();

        assertFalse(c.equals(null));
        assertFalse(c.equals(new Object()));
        assertFalse(c.equals(new Candidate("Different ID", "Different poster URL", "Different genre", "Different title")));

        assertFalse(c.equals(new Candidate("Different ID", "Different poster URL", defaultGenre,"Different title")));
        assertFalse(c.equals(new Candidate("Different ID", defaultPosterURL, "Different genre", "Different title")));
        assertFalse(c.equals(new Candidate(defaultID, "Different poster URL", "Different genre", "Different title")));
        assertFalse(c.equals(new Candidate("Different ID", "Different poster URL", "Different genre", defaultTitle)));

        assertFalse(c.equals(new Candidate("Different ID", defaultPosterURL, defaultGenre, "Different title")));
        assertFalse(c.equals(new Candidate(defaultID, "Different poster URL", defaultGenre,"Different title")));
        assertFalse(c.equals(new Candidate(defaultID, defaultPosterURL, "Different genre", "Different title")));
        assertFalse(c.equals(new Candidate("Different ID", "Different poster URL", defaultGenre, defaultTitle)));

        assertTrue(c.equals(c));
        assertTrue(c.equals(new Candidate(defaultID, defaultPosterURL, defaultGenre, defaultTitle)));
    }

    /**
     * Verify that Candidate hashCode() method evaluates to true, only if all
     * of the fields (id, poster, genre, title) hold identical values.
     */
    @Test
    public void testHashCode() {
        Candidate c = new Candidate();
        assertEquals(c.hashCode(), new Candidate().hashCode());
        assertEquals(c.hashCode(), new Candidate(defaultID, defaultPosterURL, defaultGenre, defaultTitle).hashCode());

        assertNotEquals(c.hashCode(), new Candidate("Different ID", "Different poster URL", "Different genre", "Different title").hashCode());

        assertNotEquals(c.hashCode(), new Candidate("Different ID", "Different poster URL", defaultGenre, "Different title").hashCode());
        assertNotEquals(c.hashCode(), new Candidate("Different ID", defaultPosterURL, "Different genre", "Different title").hashCode());
        assertNotEquals(c.hashCode(), new Candidate(defaultID, "Different poster URL", "Different genre", "Different title").hashCode());

        assertNotEquals(c.hashCode(), new Candidate("Different ID", defaultPosterURL, defaultGenre, "Different title").hashCode());
        assertNotEquals(c.hashCode(), new Candidate(defaultID, "Different poster URL", defaultGenre, "Different title").hashCode());
        assertNotEquals(c.hashCode(), new Candidate(defaultID, defaultPosterURL, "Different genre", "Different title").hashCode());
        assertNotEquals(c.hashCode(), new Candidate("Different ID", "Different poster URL", defaultGenre, defaultTitle).hashCode());
    }
}

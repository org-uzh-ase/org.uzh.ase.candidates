package org.uzh.ase.candidates.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "intersectLinks")
public class Candidate {

    @Id
    private String id;
    private String posterURL;

    public Candidate(String id, String posterURL) {
        this.id = id;
        this.posterURL = posterURL;
    }
    
    public Candidate() {
        this.id = "42"; // ID that most probably won't be used, so that we avoid conflicts, when using the default constructor for testing.
        this.posterURL = "https://images-na.ssl-images-amazon.com/images/M/MV5BMDU2ZWJlMjktMTRhMy00ZTA5LWEzNDgtYmNmZTEwZTViZWJkXkEyXkFqcGdeQXVyNDQ2OTk4MzI@._V1_UX182_CR0,0,182,268_AL_.jpg";
    }
    
    public String getId() {
        return id;
    }

    public String getPosterURL() {
        return posterURL;
    }

    
    public boolean equals(Candidate objCandidate) {
        return this.id.equals(objCandidate.getId()) && this.posterURL.equals(objCandidate.getPosterURL());
    }
}

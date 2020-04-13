package org.uzh.ase.candidates.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object objCandidate) {
        if (objCandidate == null) return false;
        if (objCandidate == this) return true;
        if (objCandidate instanceof Candidate) {
            return  this.id.equals(((Candidate) objCandidate).getId()) && 
                    this.posterURL.equals(((Candidate) objCandidate).getPosterURL());
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.posterURL);
    }
}

package org.uzh.ase.candidates.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "candidates")
public class Candidate {

    @Id
    private String id;
    private String poster;
    private String genre;

    public Candidate(String id, String posterURL, String genre) {
        this.id = id;
        this.poster = posterURL;
        this.genre = genre;
    }


    public Candidate() {
        this.id = "42"; // ID that most probably won't be used, so that we avoid conflicts, when using the default constructor for testing.
        this.poster = "https://images-na.ssl-images-amazon.com/images/M/MV5BMDU2ZWJlMjktMTRhMy00ZTA5LWEzNDgtYmNmZTEwZTViZWJkXkEyXkFqcGdeQXVyNDQ2OTk4MzI@._V1_UX182_CR0,0,182,268_AL_.jpg";
        this.genre = "Documentary";
    }
    
    public String getId() {
        return id;
    }

    public String getPoster() {
        return poster;
    }
    
    public String getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object objCandidate) {
        if (objCandidate == null) return false;
        if (objCandidate == this) return true;
        if (objCandidate instanceof Candidate) {
            return  this.id.equals(((Candidate) objCandidate).getId()) && 
                    this.poster.equals(((Candidate) objCandidate).getPoster()) &&
                    this.genre.equals(((Candidate) objCandidate).getGenre());
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.poster, this.genre);
    }
}

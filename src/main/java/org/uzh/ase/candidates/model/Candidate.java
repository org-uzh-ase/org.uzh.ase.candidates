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
    private String title;

    public Candidate(String id, String posterURL, String genre, String title) {
        this.id = id;
        this.poster = posterURL;
        this.genre = genre;
        this.title = title;
    }

    public Candidate() {
        this.id = "42"; // ID that most probably won't be used, so that we avoid conflicts, when using the default constructor for testing.
        this.poster = "https://images-na.ssl-images-amazon.com/images/M/MV5BMDU2ZWJlMjktMTRhMy00ZTA5LWEzNDgtYmNmZTEwZTViZWJkXkEyXkFqcGdeQXVyNDQ2OTk4MzI@._V1_UX182_CR0,0,182,268_AL_.jpg";
        this.genre = "Documentary";
        this.title = "Title of the Movie";
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

    public String getTitle(){
        return title;
    }

    @Override
    public boolean equals(Object objCandidate) {
        if (objCandidate == null) return false;
        if (objCandidate == this) return true;
        if (objCandidate instanceof Candidate) {
            return  this.id.equals(((Candidate) objCandidate).getId()) && 
                    this.poster.equals(((Candidate) objCandidate).getPoster()) &&
                    this.genre.equals(((Candidate) objCandidate).getGenre()) &&
                    this.title.equals(((Candidate) objCandidate).getTitle());
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.poster, this.genre, this.title);
    }
}

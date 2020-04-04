package org.uzh.ase.candidates.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("candidates")
public class Candidates {
    @Id
    private String id;

    private String movie1;
    private String movie2;
    private String movie3;

    public String getId() {
        return id;
    }

    public String getMovie1() {
        return movie1;
    }

    public String getMovie2() {
        return movie2;
    }

    public String getMovie3() {
        return movie3;
    }

    public Candidates(String movie1, String movie2, String movie3){
        this.movie1 = movie1;
        this.movie2 = movie2;
        this.movie3 = movie3;
    }


}

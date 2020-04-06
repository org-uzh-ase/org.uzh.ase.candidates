package org.uzh.ase.candidates.model;

public class Answer {
    private final String movie1;
    private final String movie2;
    private final String movie3;

    public String getMovie1() {
        return movie1;
    }

    public String getMovie2() {
        return movie2;
    }

    public String getMovie3() {
        return movie3;
    }

    public Answer(String movie1, String movie2, String movie3){
        this.movie1 = movie1;
        this.movie2 = movie2;
        this.movie3 = movie3;
    }

    public Answer(){
        this.movie1 = "Movie 1";
        this.movie2 = "Movie 2";
        this.movie3 = "Movie 3";
    }
}

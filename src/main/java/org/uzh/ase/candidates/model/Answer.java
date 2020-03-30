package org.uzh.ase.candidates.model;

public class Answer {
    private final String movie_1;
    private final String movie_2;
    private final String movie_3;

    public String getMovie_1() {
        return movie_1;
    }

    public String getMovie_2() {
        return movie_2;
    }

    public String getMovie_3() {
        return movie_3;
    }

    public Answer(String movie_1, String movie_2, String movie_3){
        this.movie_1 = movie_1;
        this.movie_2 = movie_2;
        this.movie_3 = movie_3;
    }

    public Answer(){
        this.movie_1 = "Movie 1";
        this.movie_2 = "Movie 2";
        this.movie_3 = "Movie 3";
    }
}

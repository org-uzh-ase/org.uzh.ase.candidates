package org.uzh.ase.candidates.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerTest {

    @Test
    public void testSimpleInit(){
        Answer answer = new Answer();

        assertEquals("Movie 1", answer.getMovie1());
        assertEquals("Movie 2", answer.getMovie2());
        assertEquals("Movie 3", answer.getMovie3());
    }

    @Test
    public void testInit(){
        Answer answer = new Answer("Movie 1", "Movie 2", "Movie 3");

        assertEquals("Movie 1", answer.getMovie1());
        assertEquals("Movie 2", answer.getMovie2());
        assertEquals("Movie 3", answer.getMovie3());
    }
}

package fr.raptao.game.tennis;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Created by raptao on 9/20/2017.
 */
public class SetScoreTest {

    @Test
    public void scoreInitialization() {
        assertEquals(0, new SetScore().currentScore());
    }

    @Test
    public void incrementAndGet() {
        SetScore score = new SetScore();
        assertTrue(score.increment());
        assertEquals(1, score.currentScore());
    }

    @Test
    public void maxScore() {
        SetScore score = new SetScore();
        assertTrue(score.increment());
        assertEquals(1, score.currentScore());
        assertTrue(score.increment());
        assertEquals(2, score.currentScore());
        assertTrue(score.increment());
        assertEquals(3, score.currentScore());
        assertTrue(score.increment());
        assertEquals(4, score.currentScore());
        assertTrue(score.increment());
        assertEquals(5, score.currentScore());
        assertTrue(score.increment());
        assertEquals(6, score.currentScore());
        assertTrue(score.increment());
        assertEquals(7, score.currentScore()); // max set score
        assertFalse(score.increment());
        assertEquals(7, score.currentScore());
    }

    @Test(expected = IllegalStateException.class)
    public void reset() {
        SetScore score = new SetScore();
        score.reset();
    }
}
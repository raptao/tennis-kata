package fr.raptao.tennis;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by raptao on 9/18/2017.
 */
public class TennisScoreTest {

    @Test
    public void newScore(){
        Score s = new TennisScore();
        assertEquals(0, s.currentScore());
    }

    @Test
    public void increment() throws Exception {
        Score s = new TennisScore();

        // O to 15
        boolean incrementState = s.increment();
        assertTrue(incrementState);
        assertEquals(15, s.currentScore());

        // 15 to 30
        incrementState = s.increment();
        assertTrue(incrementState);
        assertEquals(30, s.currentScore());

        // 30 to 40
        incrementState = s.increment();
        assertTrue(incrementState);
        assertEquals(40 , s.currentScore());

        // 40 is the max
        incrementState = s.increment();
        assertFalse(incrementState);
        assertEquals(40, s.currentScore());
    }

}
package fr.raptao.tennis;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by raptao on 9/18/2017.
 */
public class PlayerTest {


    @Test(expected = NullPointerException.class)
    public void newPlayerKO(){
        new Player(null);
    }

    @Test
    public void newPlayerOK(){
        Player p = new Player("Tennis Player");
        assertEquals("Tennis Player" , p.getName());
        assertEquals(0, p.getScore());
    }

    @Test
    public void incrementScore() {
        Player p = new Player("Tennis Player");
        assertEquals(0, p.getScore());

        boolean incrementState = p.incrementScore();
        assertTrue(incrementState);
        assertEquals(15, p.getScore());
    }

    @Test
    public void getName() {
    }

    @Test
    public void getScore() {
    }

}
package fr.raptao.tennis;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Created by raptao on 9/18/2017.
 */
public class TennisPlayerTest {


    @Test(expected = NullPointerException.class)
    public void newPlayerKO(){
        new TennisPlayer(null);
    }

    @Test
    public void newPlayerOK(){
        Player p = new TennisPlayer("Tennis TennisPlayer");
        assertNotNull(p.getName());
        assertEquals(0, p.getScore());
    }

    @Test
    public void incrementScore() {
        Player p = new TennisPlayer("Tennis TennisPlayer");
        assertEquals(0, p.getScore());

        boolean incrementState = p.incrementScore();
        assertTrue(incrementState);
        assertEquals(15, p.getScore());
    }

    @Test
    public void getName(){
        Player p = new TennisPlayer("Tennis");
        assertEquals("Tennis", p.getName());
    }

    @Test
    public void advantageTest(){
        TennisPlayer p = new TennisPlayer("Thierry");
        assertFalse(p.hasAdvantage());
        p.setAdvantage(true);
        assertTrue(p.hasAdvantage());
    }
}
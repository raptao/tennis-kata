package fr.raptao.game.tennis;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

/**
 * Created by raptao on 9/20/2017.
 */
public class TennisMatchTest {

    @Test(expected = NullPointerException.class)
    public void illegalFirstPlayer() {
        new TennisMatch(null, new TennisPlayer("test"), new TennisSet[2]);
    }


    @Test(expected = NullPointerException.class)
    public void illegalSecondPlayer() {
        new TennisMatch(new TennisPlayer("test"), null, new TennisSet[2]);
    }

    @Test(expected = NullPointerException.class)
    public void illegalSetInit() {
        new TennisMatch(new TennisPlayer("test"), new TennisPlayer("test"), null);
    }

    @Test
    public void isLastSet() throws Exception {
        TennisMatch match = new TennisMatch(new TennisPlayer("test"), new TennisPlayer("test"), new TennisSet[2]);
        assertFalse(match.isLastSet());
    }

}
package fr.raptao.tennis;

import org.junit.Test;

/**
 * Created by raptao on 9/20/2017.
 */
public class TennisSetTest {

    @Test(expected = NullPointerException.class)
    public void betweenKOFirstPlayer() {
        TennisSet.between(null, "");
    }

    @Test(expected = NullPointerException.class)
    public void betweenKOSecondPlayer() {
        TennisSet.between("", null);
    }

}
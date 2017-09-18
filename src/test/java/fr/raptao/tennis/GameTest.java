package fr.raptao.tennis;

import org.junit.Test;

/**
 * Created by raptao on 9/18/2017.
 */
public class GameTest {
    @Test(expected = NullPointerException.class)
    public void betweenKOFirstPlayer(){
        Game.between(null, "");
    }
    
    @Test(expected = NullPointerException.class)
    public void betweenKOSecondPlayer(){
        Game.between("", null);
    }
}
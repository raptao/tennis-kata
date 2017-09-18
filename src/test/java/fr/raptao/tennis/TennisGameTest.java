package fr.raptao.tennis;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by raptao on 9/18/2017.
 */
public class TennisGameTest {
    @Test(expected = NullPointerException.class)
    public void betweenKOFirstPlayer() {
        TennisGame.between(null, "");
    }

    @Test(expected = NullPointerException.class)
    public void betweenKOSecondPlayer() {
        TennisGame.between("", null);
    }

    @Test
    public void incrementFirstPlayer() {
        TennisGame game = TennisGame.between("Thierry", "Raptao");
        game.incrementFirstPlayer();

        assertEquals(0, game.getSecondPlayer().getScore());
        assertEquals(15, game.getFirstPlayer().getScore());
    }

    @Test
    public void incrementSecondPlayer() {
        TennisGame game = TennisGame.between("Thierry", "Raptao");
        game.incrementSecondPlayer();
        assertEquals(0, game.getFirstPlayer().getScore());
        assertEquals(15, game.getSecondPlayer().getScore());
    }

    @Test
    public void isDeuce(){
        TennisGame game = TennisGame.between("Thierry", "Raptao");
        assertFalse(game.isDeuce()); // 0 - 0
        game.incrementFirstPlayer();
        assertFalse(game.isDeuce()); // 15 - 0
        game.incrementSecondPlayer();
        game.incrementSecondPlayer();
        assertFalse(game.isDeuce()); // 15 - 30

        game.incrementFirstPlayer();
        game.incrementFirstPlayer();
        game.incrementSecondPlayer();
        assertTrue(game.isDeuce()); // 40 - 40
    }
}
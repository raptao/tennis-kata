package fr.raptao.game.tennis;

import fr.raptao.game.Player;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by raptao on 9/20/2017.
 */
public class TennisSetTest {

    @Test(expected = NullPointerException.class)
    public void betweenKOFirstPlayer() {
        TennisSet.between(null, new TennisPlayer("test"));
    }

    @Test(expected = NullPointerException.class)
    public void betweenKOSecondPlayer() {
        TennisSet.between(new TennisPlayer("test"), null);
    }


    @Test(expected = NullPointerException.class)
    public void betweenKOFirstPlayerName() {
        TennisSet.between(null, "");
    }

    @Test(expected = NullPointerException.class)
    public void betweenKOSecondPlayerName() {
        TennisSet.between("", null);
    }

    @Test
    public void firstPlayerDomination(){
        TennisSet set = TennisSet.between("Thierry", "Raptao");
        assertEquals(0, set.firstPlayerScore());
        assertEquals(0, set.secondPlayerScore());

        assertTrue(set.incrementFirstPlayer());
        assertEquals(1, set.firstPlayerScore());

        assertTrue(set.incrementFirstPlayer());
        assertEquals(2, set.firstPlayerScore());

        assertTrue(set.incrementFirstPlayer());
        assertEquals(3, set.firstPlayerScore());

        assertTrue(set.incrementFirstPlayer());
        assertEquals(4, set.firstPlayerScore());

        assertTrue(set.incrementFirstPlayer());
        assertEquals(5, set.firstPlayerScore());

        assertFalse(set.incrementFirstPlayer());
        assertEquals(6, set.firstPlayerScore());
        assertFalse(set.incrementFirstPlayer()); // already won

        Optional<Player> winner = set.getWinner();
        assertTrue(winner.isPresent());
        assertEquals("Thierry", set.getWinner().get().getName());
    }

    @Test
    public void secondPlayerDomination(){
        TennisSet set = TennisSet.between("Thierry", "Raptao");
        assertEquals(0, set.secondPlayerScore());
        assertEquals(0, set.firstPlayerScore());

        assertTrue(set.incrementSecondPlayer());
        assertEquals(1, set.secondPlayerScore());

        assertTrue(set.incrementSecondPlayer());
        assertEquals(2, set.secondPlayerScore());

        assertTrue(set.incrementSecondPlayer());
        assertEquals(3, set.secondPlayerScore());

        assertTrue(set.incrementSecondPlayer());
        assertEquals(4, set.secondPlayerScore());

        assertTrue(set.incrementSecondPlayer());
        assertEquals(5, set.secondPlayerScore());

        assertFalse(set.incrementSecondPlayer());
        assertEquals(6, set.secondPlayerScore());

        assertFalse(set.incrementSecondPlayer());
        Optional<Player> winner = set.getWinner();
        assertTrue(winner.isPresent());
        assertEquals("Raptao", set.getWinner().get().getName());
    }

    @Test
    public void firstPlayerWinsByTieBreak(){
        TennisSet set = TennisSet.between("Thierry", "Raptao");
        set.incrementFirstPlayer();
        set.incrementSecondPlayer(); // 1 -1
        set.incrementFirstPlayer();
        set.incrementSecondPlayer(); // 2 - 2
        set.incrementFirstPlayer();
        set.incrementSecondPlayer(); // 3 - 3
        set.incrementFirstPlayer();
        set.incrementSecondPlayer(); // 4 - 4
        set.incrementFirstPlayer();
        set.incrementSecondPlayer(); // 5 - 5
        assertTrue(set.incrementSecondPlayer());  // 5 - 6
        assertFalse(set.isFinished());
        assertTrue(set.incrementFirstPlayer()); // 6 - 6
        assertFalse(set.isFinished());
        assertTrue(set.incrementFirstPlayer()); // 7 - 6
        assertFalse(set.isFinished());
        assertTrue(set.incrementSecondPlayer());  // 7 - 7
        assertFalse(set.isFinished());

        assertTrue(set.incrementFirstPlayer()); // 8 - 7
        assertFalse(set.isFinished());

        assertFalse(set.incrementFirstPlayer()); // 9 - 7
        assertTrue(set.isFinished());
        assertEquals("Thierry", set.getWinner().get().getName());
    }

    @Test
    public void secondPlayerWinsByTieBreak(){
        TennisSet set = TennisSet.between("Thierry", "Raptao");
        set.incrementFirstPlayer();
        set.incrementSecondPlayer(); // 1 -1
        set.incrementFirstPlayer();
        set.incrementSecondPlayer(); // 2 - 2
        set.incrementFirstPlayer();
        set.incrementSecondPlayer(); // 3 - 3
        set.incrementFirstPlayer();
        set.incrementSecondPlayer(); // 4 - 4
        set.incrementFirstPlayer();
        set.incrementSecondPlayer(); // 5 - 5
        assertTrue(set.incrementFirstPlayer());  // 6 - 5
        assertFalse(set.isFinished());
        assertTrue(set.incrementSecondPlayer()); // 6 - 6
        assertFalse(set.isFinished());
        assertTrue(set.incrementSecondPlayer()); // 6 - 7
        assertFalse(set.isFinished());
        assertTrue(set.incrementFirstPlayer());  // 7 - 7
        assertFalse(set.isFinished());

        assertTrue(set.incrementSecondPlayer()); // 7 - 8
        assertFalse(set.isFinished());

        assertFalse(set.incrementSecondPlayer()); // 7 - 9
        assertTrue(set.isFinished());
        assertEquals("Raptao", set.getWinner().get().getName());
    }
    @Test
    public void getWinner(){
        TennisSet set = TennisSet.between("Thierry", "Raptao");
        assertFalse(set.getWinner().isPresent());
    }

}
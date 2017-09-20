package fr.raptao.tennis;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by raptao on 9/18/2017.
 */
public class TennisGame {

    public static final int MAX_SCORE = 40;
    private final TennisPlayer firstPlayer;
    private final TennisPlayer secondPlayer;
    private WinningPlayer winningPlayer;

    private TennisGame(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
        this.firstPlayer = Objects.requireNonNull(firstPlayer);
        this.secondPlayer = Objects.requireNonNull(secondPlayer);
        this.winningPlayer = WinningPlayer.NONE;
    }

    /**
     * Creates a newly initialized {@link TennisGame} object between two players.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     * @return a new game;
     */
    public static TennisGame between(String firstPlayer, String secondPlayer) {
        TennisPlayer one = new TennisPlayer(firstPlayer);
        TennisPlayer two = new TennisPlayer(secondPlayer);
        return new TennisGame(one, two);
    }

    /**
     * Increments the first player score. If the game is finished this method returns false.
     *
     * @return true if the score has been incremented, false otherwise
     */
    public boolean incrementFirstPlayer() {
        if (isFinished()) {
            return false;
        }
        // return to deuce game
        if (secondPlayer.hasAdvantage()) {
            secondPlayer.setAdvantage(false);
            return true;
        }
        // player gets advantage
        if (isDeuce()) {
            firstPlayer.setAdvantage(true);
            secondPlayer.setAdvantage(false);
            return true;
        }
        // game won
        if (firstPlayer.getScore() == MAX_SCORE || firstPlayer.hasAdvantage()) {
            winningPlayer = WinningPlayer.PLAYER_ONE;
            return false;
        }
        return firstPlayer.incrementScore();
    }

    public boolean incrementSecondPlayer() {
        if (isFinished()) {
            return false;
        }
        if (firstPlayer.hasAdvantage()) {
            firstPlayer.setAdvantage(false);
            return true;
        }
        if (isDeuce()) {
            secondPlayer.setAdvantage(true);
            firstPlayer.setAdvantage(false);
            return true;
        }
        if (secondPlayer.getScore() == MAX_SCORE || secondPlayer.hasAdvantage()) {
            winningPlayer = WinningPlayer.PLAYER_TWO;
            return false;
        }
        return secondPlayer.incrementScore();
    }

    /**
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished() {
        return !winningPlayer.equals(WinningPlayer.NONE);
    }

    public boolean isDeuce() {
        if(firstPlayer.hasAdvantage() || secondPlayer.hasAdvantage()){
            return false;
        }
        return (firstPlayer.getScore() == MAX_SCORE && secondPlayer.getScore() == MAX_SCORE);
    }

    public TennisPlayer getFirstPlayer() {
        return firstPlayer;
    }

    public TennisPlayer getSecondPlayer() {
        return secondPlayer;
    }

    public Optional<Player> getWinningPlayer() {
        if (winningPlayer.equals(WinningPlayer.NONE)) {
            return Optional.empty();
        }
        return winningPlayer.equals(WinningPlayer.PLAYER_ONE) ?
                Optional.of(firstPlayer) :
                Optional.of(secondPlayer);
    }
}

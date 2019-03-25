package fr.raptao.game.tennis;

import fr.raptao.game.Game;

import java.util.Objects;

/**
 * Created by raptao on 9/18/2017.
 */
public class TennisGame implements Game<TennisPlayer> {

    private static final int MAX_SCORE = 40;
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
    static TennisGame between(String firstPlayer, String secondPlayer) {
        TennisPlayer one = new TennisPlayer(firstPlayer);
        TennisPlayer two = new TennisPlayer(secondPlayer);
        return new TennisGame(one, two);
    }

    static TennisGame between(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
        return new TennisGame(firstPlayer, secondPlayer);
    }

    /**
     * Increments the first player score. If the game is finished this method returns false.
     *
     * @return true if the score has been incremented, false otherwise
     */
    @Override
    public boolean incrementFirstPlayer() {
        return this.incrementPlayerScore(firstPlayer, secondPlayer);
    }

    @Override
    public boolean incrementSecondPlayer() {
        return this.incrementPlayerScore(secondPlayer, firstPlayer);
    }

    private boolean incrementPlayerScore(TennisPlayer toIncrement, TennisPlayer opponent) {
        if (isFinished()) {
            return false;
        }
        if (opponent.hasAdvantage()) {
            opponent.setAdvantage(false);
            return true;
        }
        if (isDeuce()) {
            toIncrement.setAdvantage(true);
            opponent.setAdvantage(false);
            return true;
        }
        if (toIncrement.getScore() == MAX_SCORE || toIncrement.hasAdvantage()) {
            winningPlayer = WinningPlayer.PLAYER_TWO;
            return false;
        }
        return toIncrement.incrementScore();
    }

    /**
     * @return true if the game is finished, false otherwise
     */
    @Override
    public boolean isFinished() {
        return !winningPlayer.equals(WinningPlayer.NONE);
    }

    boolean isDeuce() {
        if (firstPlayer.hasAdvantage() || secondPlayer.hasAdvantage()) {
            return false;
        }
        return (firstPlayer.getScore() == MAX_SCORE && secondPlayer.getScore() == MAX_SCORE);
    }

    @Override
    public TennisPlayer getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public TennisPlayer getSecondPlayer() {
        return secondPlayer;
    }

    @Override
    public int firstPlayerScore() {
        return firstPlayer.getScore();
    }

    @Override
    public int secondPlayerScore() {
        return secondPlayer.getScore();
    }

    @Override
    public WinningPlayer getWinningPlayer() {
        return winningPlayer;
    }

    void reset() {
        firstPlayer.resetScore();
        secondPlayer.resetScore();
        winningPlayer = WinningPlayer.NONE;
    }
}

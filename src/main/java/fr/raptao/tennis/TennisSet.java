package fr.raptao.tennis;

import javafx.util.Pair;

import java.util.Objects;

/**
 * Created by raptao on 9/20/2017.
 */
public class TennisSet implements Game {
    public static final int MAX_SCORE = 40;
    private final Pair<TennisPlayer, SetScore> firstPlayer;
    private final Pair<TennisPlayer, SetScore> secondPlayer;
    private WinningPlayer winningPlayer;

    private TennisSet(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
        this.firstPlayer = new Pair<>(Objects.requireNonNull(firstPlayer), new SetScore());
        this.secondPlayer = new Pair<>(Objects.requireNonNull(secondPlayer), new SetScore());
        this.winningPlayer = WinningPlayer.NONE;
    }

    /**
     * Creates a newly initialized {@link TennisGame} object between two players.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     * @return a new game;
     */
    public static TennisSet between(String firstPlayer, String secondPlayer) {
        TennisPlayer one = new TennisPlayer(firstPlayer);
        TennisPlayer two = new TennisPlayer(secondPlayer);
        return new TennisSet(one, two);
    }

    public static TennisSet between(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
        return new TennisSet(firstPlayer, secondPlayer);
    }

    private static boolean isWinning(Pair<TennisPlayer, SetScore> player, Pair<TennisPlayer, SetScore> opponent) {
        return opponent.getValue().currentScore() <= 4 && player.getValue().currentScore() == 5;
    }

    private static boolean winningBySeven(Pair<TennisPlayer, SetScore> player, Pair<TennisPlayer, SetScore> opponent) {
        return opponent.getValue().currentScore() >= 5 && player.getValue().currentScore() >= 6;
    }

    @Override
    public boolean incrementFirstPlayer() {
        if (isFinished()) {
            return false;
        }
        if (winningBySeven(firstPlayer, secondPlayer)) {
            firstPlayer.getValue().increment();
            winningPlayer = WinningPlayer.PLAYER_ONE;
            return false;
        }
        if (isWinning(firstPlayer, secondPlayer)) {
            firstPlayer.getValue().increment();
            winningPlayer = WinningPlayer.PLAYER_ONE;
            return false;
        }
        return firstPlayer.getValue().increment();
    }

    @Override
    public boolean incrementSecondPlayer() {
        if (isFinished()) {
            return false;
        }
        if (winningBySeven(secondPlayer, firstPlayer)) {
            secondPlayer.getValue().increment();
            winningPlayer = WinningPlayer.PLAYER_TWO;
            return false;
        }

        if (isWinning(secondPlayer, firstPlayer)) {
            secondPlayer.getValue().increment();
            winningPlayer = WinningPlayer.PLAYER_TWO;
            return false;
        }
        return secondPlayer.getValue().increment();
    }

    @Override
    public boolean isFinished() {
        return !winningPlayer.equals(WinningPlayer.NONE);
    }

    @Override
    public TennisPlayer getFirstPlayer() {
        return firstPlayer.getKey();
    }

    @Override
    public TennisPlayer getSecondPlayer() {
        return secondPlayer.getKey();
    }

    @Override
    public int firstPlayerScore() {
        return firstPlayer.getValue().currentScore();
    }

    @Override
    public int secondPlayerScore() {
        return secondPlayer.getValue().currentScore();
    }

    @Override
    public WinningPlayer getWinningPlayer() {
        return winningPlayer;
    }

}

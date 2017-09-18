package fr.raptao.tennis;

import java.util.Objects;

/**
 * Created by raptao on 9/18/2017.
 */
public class TennisGame {

    public static final int MAX_SCORE = 40;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private Player advantagedPlayer;

    private TennisGame(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = Objects.requireNonNull(firstPlayer);
        this.secondPlayer = Objects.requireNonNull(secondPlayer);
    }

    /**
     * Creates a newly initialized {@link TennisGame} object between two players.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     * @return a new game;
     */
    public static TennisGame between(String firstPlayer, String secondPlayer) {
        Player one = new TennisPlayer(firstPlayer);
        Player two = new TennisPlayer(secondPlayer);
        return new TennisGame(one, two);
    }

    public boolean incrementFirstPlayer() {
        return firstPlayer.incrementScore();
    }

    public boolean incrementSecondPlayer() {
        return secondPlayer.incrementScore();
    }

    public boolean isDeuce() {
        return firstPlayer.getScore() == secondPlayer.getScore() && firstPlayer.getScore() == MAX_SCORE;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }
}

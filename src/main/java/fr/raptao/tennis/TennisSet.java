package fr.raptao.tennis;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by raptao on 9/20/2017.
 */
public class TennisSet implements Game{
    public static final int MAX_SCORE = 40;
    private final TennisPlayer firstPlayer;
    private final TennisPlayer secondPlayer;

    private TennisSet(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
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
    public static TennisSet between(String firstPlayer, String secondPlayer) {
        TennisPlayer one = new TennisPlayer(firstPlayer);
        TennisPlayer two = new TennisPlayer(secondPlayer);
        return new TennisSet(one, two);
    }

    public static TennisSet between(TennisPlayer firstPlayer, TennisPlayer secondPlayer){
        return new TennisSet(firstPlayer, secondPlayer);
    }

    @Override
    public boolean incrementFirstPlayer() {
        return false;
    }

    @Override
    public boolean incrementSecondPlayer() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
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
    public Optional<Player> getWinningPlayer() {
        return null;
    }
}

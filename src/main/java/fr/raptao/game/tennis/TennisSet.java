package fr.raptao.game.tennis;

import fr.raptao.game.Game;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Objects;

public class TennisSet implements Game {
    private final Pair<TennisPlayer, SetScore> firstPlayer;
    private final Pair<TennisPlayer, SetScore> secondPlayer;
    private WinningPlayer winningPlayer;
    private boolean tieBreak;

    private TennisSet(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
        this.firstPlayer = Pair.of(Objects.requireNonNull(firstPlayer), new SetScore());
        this.secondPlayer = Pair.of(Objects.requireNonNull(secondPlayer), new SetScore());
        this.winningPlayer = WinningPlayer.NONE;
    }

    /**
     * Creates a newly initialized {@link TennisGame} object between two players.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     * @return a new game;
     */
    static TennisSet between(String firstPlayer, String secondPlayer) {
        TennisPlayer one = new TennisPlayer(firstPlayer);
        TennisPlayer two = new TennisPlayer(secondPlayer);
        return new TennisSet(one, two);
    }

    static TennisSet between(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
        return new TennisSet(firstPlayer, secondPlayer);
    }

    private static boolean isWinning(Pair<TennisPlayer, SetScore> player, Pair<TennisPlayer, SetScore> opponent) {
        return opponent.getValue().currentScore() <= 4 && player.getValue().currentScore() == 5;
    }

    private static boolean tieBreakRule(Pair<TennisPlayer, SetScore> scoringPlayer,
                                        Pair<TennisPlayer, SetScore> opponent,
                                        Runnable winAction) {
        if (scoringPlayer.getValue().currentScore() == (opponent.getValue().currentScore() + 1)) {
            winAction.run();
            return false;
        }
        return scoringPlayer.getValue().forceIncrement();
    }

    @Override
    public boolean incrementFirstPlayer() {
        if (isFinished()) {
            return false;
        }
        if (tieBreak) {
            return tieBreakRule(firstPlayer, secondPlayer, this::firstPlayerWins);
        }
        if (firstPlayerScore() == 5 && secondPlayerScore() == 6) {
            tieBreak = true;
            return firstPlayer.getValue().increment();
        }
        if (isWinning(firstPlayer, secondPlayer)) {
            firstPlayerWins();
            return false;
        }
        return firstPlayer.getValue().increment();
    }

    private void firstPlayerWins() {
        firstPlayer.getValue().increment();
        winningPlayer = WinningPlayer.PLAYER_ONE;
    }

    @Override
    public boolean incrementSecondPlayer() {
        if (isFinished()) {
            return false;
        }
        if (tieBreak) {
            return tieBreakRule(secondPlayer, firstPlayer, this::secondPlayerWins);
        }
        if (firstPlayerScore() == 6 && secondPlayerScore() == 5) {
            tieBreak = true;
            return secondPlayer.getValue().increment();
        }
        if (isWinning(secondPlayer, firstPlayer)) {
            secondPlayerWins();
            return false;
        }
        return secondPlayer.getValue().increment();
    }

    private void secondPlayerWins() {
        secondPlayer.getValue().increment();
        winningPlayer = WinningPlayer.PLAYER_TWO;
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

package fr.raptao.tennis;

import java.util.Optional;

/**
 * Created by raptao on 9/20/2017.
 */
public interface Game {
    boolean incrementFirstPlayer();

    boolean incrementSecondPlayer();

    boolean isFinished();

    TennisPlayer getFirstPlayer();

    TennisPlayer getSecondPlayer();

    Optional<Player> getWinningPlayer();
}

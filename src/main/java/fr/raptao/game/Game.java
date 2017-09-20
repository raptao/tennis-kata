package fr.raptao.game;

import fr.raptao.game.tennis.TennisPlayer;
import fr.raptao.game.tennis.WinningPlayer;

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

    public int firstPlayerScore();

    public int secondPlayerScore();

    WinningPlayer getWinningPlayer();

    default Optional<Player> getWinner(){
        if (getWinningPlayer().equals(WinningPlayer.NONE)) {
            return Optional.empty();
        }
        return getWinningPlayer().equals(WinningPlayer.PLAYER_ONE) ?
                Optional.of(getFirstPlayer()) :
                Optional.of(getSecondPlayer());
    }
}

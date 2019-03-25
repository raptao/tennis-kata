package fr.raptao.game;

import fr.raptao.game.tennis.WinningPlayer;

import java.util.Optional;

public interface Game<E extends Player> {

    boolean incrementFirstPlayer();

    boolean incrementSecondPlayer();

    boolean isFinished();

    E getFirstPlayer();

    E getSecondPlayer();

    int firstPlayerScore();

    int secondPlayerScore();

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

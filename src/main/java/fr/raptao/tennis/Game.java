package fr.raptao.tennis;

import java.util.Objects;

/**
 * Created by raptao on 9/18/2017.
 */
public class Game {

    private final Player firstPlayer;
    private final Player secondPlayer;

    private Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = Objects.requireNonNull(firstPlayer);
        this.secondPlayer = Objects.requireNonNull(secondPlayer);
    }

    /**
     * Creates a newly initialized {@link Game} object between two players.
     * @param firstPlayer the first player
     * @param secondPlayer the second player
     * @return a new game;
     */
    public static Game between(String firstPlayer, String secondPlayer){
        Player one = new Player(firstPlayer);
        Player two = new Player(secondPlayer);
        return new Game(one, two);
    }
}

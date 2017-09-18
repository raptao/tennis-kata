package fr.raptao.tennis;

import java.util.Objects;

/**
 * Created by raptao on 9/18/2017.
 */
public class Player {

    private final String name;
    private final Score score;

    public Player(String name) {
        this.name = Objects.requireNonNull(name, "Player name should not be null");
        this.score = new Score();
    }

    public boolean incrementScore() {
        return score.increment();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score.currentScore();
    }
}

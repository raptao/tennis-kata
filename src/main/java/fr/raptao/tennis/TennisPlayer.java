package fr.raptao.tennis;

import java.util.Objects;

/**
 * Created by raptao on 9/18/2017.
 */
public class TennisPlayer implements Player {

    private final String name;
    private final Score score;

    public TennisPlayer(String name) {
        this.name = Objects.requireNonNull(name, "TennisPlayer name should not be null");
        this.score = new TennisScore();
    }

    @Override
    public boolean incrementScore() {
        return score.increment();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return score.currentScore();
    }
}

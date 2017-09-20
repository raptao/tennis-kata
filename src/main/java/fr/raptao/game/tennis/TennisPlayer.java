package fr.raptao.game.tennis;

import fr.raptao.game.Player;
import fr.raptao.game.Score;

import java.util.Objects;

/**
 * Created by raptao on 9/18/2017.
 */
public class TennisPlayer implements Player {

    private final String name;
    private final Score score;
    private boolean hasAdvantage;

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

    public boolean hasAdvantage(){
        return hasAdvantage;
    }

    public void setAdvantage( boolean advantage){
        hasAdvantage = advantage;
    }

    public void resetScore(){
        score.reset();
    }
}

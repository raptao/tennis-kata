package fr.raptao.game.tennis;

import fr.raptao.game.Score;

/**
 * Created by raptao on 9/20/2017.
 */
public class SetScore implements Score {

    private final static int MAX_SCORE = 7;

    private int score;

    @Override
    public boolean increment() {
        if( score >= MAX_SCORE){
            return false;
        }
        score ++;
        return true;
    }

    public boolean forceIncrement(){
        score ++;
        return true;
    }

    @Override
    public int currentScore() {
        return score;
    }

    @Override
    public void reset() {
    }
}

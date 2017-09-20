package fr.raptao.game;

/**
 * Created by raptao on 9/19/2017.
 */
public interface Score {
    boolean increment();

    int currentScore();

    public void reset();
}

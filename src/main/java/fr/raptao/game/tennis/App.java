package fr.raptao.game.tennis;

/**
 * Created by raptao on 9/20/2017.
 */
public class App {

    public static void main(String[] args) {
        TennisMatch match = TennisMatch.tennisMatchPrompt();
        match.run();
    }
}

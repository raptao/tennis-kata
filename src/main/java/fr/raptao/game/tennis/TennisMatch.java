package fr.raptao.game.tennis;

import fr.raptao.game.Player;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;

public class TennisMatch {

    private static final int SET_LIMIT = 2;
    private static final Scanner scanner = new Scanner(System.in);

    private final TennisPlayer firstPlayer;
    private final TennisPlayer secondPlayer;
    private final TennisSet[] sets;
    private boolean lastSet;
    private boolean gameOver;
    private Player winner;

    public TennisMatch(TennisPlayer firstPlayer, TennisPlayer secondPlayer, TennisSet[] sets) {
        this.firstPlayer = Objects.requireNonNull(firstPlayer);
        this.secondPlayer = Objects.requireNonNull(secondPlayer);
        this.sets = Objects.requireNonNull(sets);
    }

    public static TennisMatch tennisMatchPrompt() {
        System.out.print("Player 1 name :");
        TennisPlayer firstPlayer = new TennisPlayer(scanner.nextLine());
        System.out.print("Player 2 name :");
        TennisPlayer secondPlayer = new TennisPlayer(scanner.nextLine());
        TennisSet[] sets = new TennisSet[SET_LIMIT];
        sets[0] = TennisSet.between(firstPlayer, secondPlayer);
        sets[1] = TennisSet.between(firstPlayer, secondPlayer);
        return new TennisMatch(firstPlayer, secondPlayer, sets);
    }

    private static void usage() {
        System.out.println("COMMANDS:");
        System.out.println("'1' : increment first player score");
        System.out.println("'2' : increment second player score");
        System.out.println("'d' : display score");
    }

    public boolean isLastSet() {
        return lastSet;
    }

    public void run() {
        try {
            TennisGame game = TennisGame.between(firstPlayer, secondPlayer);
            boolean gameWon;
            while (!gameOver) {
                System.out.print("COMMAND> ");
                String command = scanner.nextLine();
                switch (command) {
                    case "1":
                        gameWon = applyIncrement(game::incrementFirstPlayer, currentSet()::incrementFirstPlayer, firstPlayer);
                        if (gameWon) {
                            game.reset();
                        }
                        break;
                    case "2":
                        gameWon = applyIncrement(game::incrementSecondPlayer, currentSet()::incrementSecondPlayer, secondPlayer);
                        if (gameWon) {
                            game.reset();
                        }
                        break;
                    case "d":
                        if (isLastSet()) {
                            System.out.println(String.format("%s:\n SCORE : %d \t SETS : %d | %d", firstPlayer.getName(),
                                    game.firstPlayerScore(), sets[0].firstPlayerScore(), currentSet().firstPlayerScore()));
                            System.out.println(String.format("%s:\n SCORE : %d \t SETS : %d | %d", secondPlayer.getName(),
                                    game.secondPlayerScore(), sets[0].secondPlayerScore()));
                        } else {
                            System.out.println(String.format("%s:\n SCORE : %d \t SET : %d", firstPlayer.getName(),
                                    game.firstPlayerScore(), sets[0].firstPlayerScore(), currentSet().firstPlayerScore()));
                            System.out.println(String.format("%s:\n SCORE : %d \t SET : %d", secondPlayer.getName(),
                                    game.secondPlayerScore(), sets[0].secondPlayerScore()));
                        }
                        System.out.println("------------------------------");
                        break;
                    default:
                        usage();
                        break;
                }
                if (winner != null) {
                    System.out.println("MATCH WON by " + winner.getName());
                    break;
                }
            }
        } finally {
            scanner.close();
        }
    }

    /**
     * @param gameAction
     * @param setAction
     * @param ifGameWon
     * @return true if the game is won, false otherwise
     */
    private boolean applyIncrement(Supplier<Boolean> gameAction, Supplier<Boolean> setAction, Player ifGameWon) {
        Boolean result = gameAction.get();
        // if game won
        if (!result) {
            boolean setWon = !setAction.get();
            if (setWon && isLastSet()) {
                gameOver = true;
                winner = ifGameWon;
            }
        }
        return !result;
    }

    private TennisSet currentSet() {
        if (sets[0].isFinished()) {
            lastSet = true;
            return sets[1];
        } else return sets[0];
    }
}

package fr.raptao.game.tennis;

import fr.raptao.game.Player;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;

public class TennisMatch {

    private static final int SET_LIMIT = 2;

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
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Player 1 name :");
            TennisPlayer firstPlayer = new TennisPlayer(scanner.nextLine());
            System.out.print("Player 2 name :");
            TennisPlayer secondPlayer = new TennisPlayer(scanner.nextLine());
            TennisSet[] sets = new TennisSet[SET_LIMIT];
            sets[0] = TennisSet.between(firstPlayer, secondPlayer);
            sets[1] = TennisSet.between(firstPlayer, secondPlayer);
            return new TennisMatch(firstPlayer, secondPlayer, sets);
        }
    }

    public boolean isLastSet() {
        return lastSet;
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            TennisGame game = TennisGame.between(firstPlayer, secondPlayer);
            while (scanner.hasNextLine() && !gameOver) {
                String command = scanner.nextLine();
                switch (command) {
                    case "1":
                        applyIncrement(game::incrementFirstPlayer, firstPlayer);
                        break;
                    case "2":
                        applyIncrement(game::incrementSecondPlayer, secondPlayer);
                        break;
                    case "d":
                        if (isLastSet()) {
                            System.out.println(String.format("%s:\n SCORE : %d \t SETS : %d | %d", firstPlayer.getName(),
                                    game.firstPlayerScore(), sets[0].firstPlayerScore(), currentSet().firstPlayerScore()));
                            System.out.println(String.format("%s:\n SCORE : %d \t SETS : %d | %d", secondPlayer.getName(),
                                    game.firstPlayerScore(), sets[0].secondPlayerScore()));
                        } else {
                            System.out.println(String.format("%s:\n SCORE : %d \t SET : %d", firstPlayer.getName(),
                                    game.firstPlayerScore(), sets[0].firstPlayerScore(), currentSet().firstPlayerScore()));
                            System.out.println(String.format("%s:\n SCORE : %d \t SET : %d", secondPlayer.getName(),
                                    game.firstPlayerScore(), sets[0].secondPlayerScore()));
                        }
                        System.out.println("------------------------------");
                        break;
                    default:
                        break;
                }
                if (winner != null) {
                    System.out.println("MATCH WON by " + winner.getName());
                    break;
                }
            }
        }
    }

    private void applyIncrement(Supplier<Boolean> supplier, Player ifGameWon) {
        Boolean result = supplier.get();
        if (result) {
            boolean setWon = currentSet().incrementSecondPlayer();
            if (setWon && isLastSet()) {
                gameOver = true;
                winner = ifGameWon;
            }
        }
    }

    private TennisSet currentSet() {
        if (sets[0].isFinished()) {
            lastSet = true;
            return sets[1];
        } else return sets[0];
    }
}

package fr.raptao.game;

import fr.raptao.game.tennis.TennisPlayer;

public interface TennisRuleResult {

    TennisRuleResultType getType();

    TennisPlayer getScorer();

}

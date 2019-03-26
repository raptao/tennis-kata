package fr.raptao.game;

public interface Rule {

    boolean canBeApplied();

    TennisRuleResult getResult();
}

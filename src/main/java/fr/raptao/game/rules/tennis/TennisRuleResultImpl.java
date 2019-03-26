package fr.raptao.game.rules.tennis;

import fr.raptao.game.TennisRuleResult;
import fr.raptao.game.TennisRuleResultType;
import fr.raptao.game.tennis.TennisPlayer;

public class TennisRuleResultImpl implements TennisRuleResult {

    private TennisRuleResultType type;
    private TennisPlayer scorer;

    public TennisRuleResultImpl(TennisRuleResultType type, TennisPlayer scorer) {
        this.type = type;
        this.scorer = scorer;
    }

    @Override
    public TennisRuleResultType getType() {
        return this.type;
    }

    @Override
    public TennisPlayer getScorer() {
        return this.scorer;
    }
}

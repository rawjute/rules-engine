package com.rawjute.rulesengine.engine;

import com.rawjute.rulesengine.rule.ParameterizedRuleAction;
import com.rawjute.rulesengine.rule.VoidRuleAction;
import com.rawjute.rulesengine.rule.Rule;


public class RulesEngineActionManager {

    private final Rule rule;

    RulesEngineActionManager(Rule rule) {
        this.rule = rule;
    }

    public RulesEngineActionManager then(ParameterizedRuleAction<?> ruleAction) {
        rule.addAction(ruleAction);
        return this;
    }

    public RulesEngineActionManager then(VoidRuleAction ruleAction) {
        rule.addAction(ruleAction);
        return this;
    }

    public Rule build() {
        return rule;
    }
}
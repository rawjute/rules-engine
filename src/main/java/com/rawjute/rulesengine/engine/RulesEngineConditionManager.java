package com.rawjute.rulesengine.engine;

import com.rawjute.rulesengine.rule.ParameterizedRuleAction;
import com.rawjute.rulesengine.rule.VoidRuleAction;
import com.rawjute.rulesengine.rule.RuleCondition;
import com.rawjute.rulesengine.rule.Rule;

public class RulesEngineConditionManager {

    private final RulesEngineActionManager rulesEngineActionManager;
    private final Rule rule;

    RulesEngineConditionManager(RulesEngineManager rulesEngineManager, Rule rule) {
        this.rulesEngineActionManager = new RulesEngineActionManager(rulesEngineManager, rule);
        this.rule = rule;
    }

    public RulesEngineConditionManager and(RuleCondition ruleCondition) {
        rule.addCondition(ruleCondition);
        return this;
    }

    public RulesEngineActionManager then(ParameterizedRuleAction<?> ruleAction) {
        return rulesEngineActionManager.then(ruleAction);
    }

    public RulesEngineActionManager then(VoidRuleAction ruleAction) {
        return rulesEngineActionManager.then(ruleAction);
    }

}
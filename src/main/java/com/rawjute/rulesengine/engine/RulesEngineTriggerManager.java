package com.rawjute.rulesengine.engine;

import com.rawjute.rulesengine.rule.Rule;

public class RulesEngineTriggerManager {

    private final Rule rule;

    RulesEngineTriggerManager(Rule rule) {
        this.rule = rule;
    }

    public RulesEngineConditionManager when(String trigger) {
        rule.setTrigger(trigger);
        return new RulesEngineConditionManager(rule);
    }

}

package com.rawjute.rulesengine.engine;

import com.rawjute.rulesengine.rule.Rule;

public class RulesEngineBuilder {

    private RulesEngineBuilder() {
    }

    public static RulesEngineTriggerManager newRule(String ruleId) {
        return new RulesEngineTriggerManager(new Rule(ruleId));
    }

}

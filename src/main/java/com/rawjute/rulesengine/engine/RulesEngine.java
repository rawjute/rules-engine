package com.rawjute.rulesengine.engine;

import com.rawjute.rulesengine.rule.Rule;

public interface RulesEngine {

    RulesEngine removeRule(String ruleId);

    RulesEngine saveRule(Rule rule);

    void evaluateRules(String triggerName);
}

package com.rawjute.rulesengine.rule;

import java.util.LinkedList;
import java.util.List;

public class Rule {

    private final List<RuleCondition> conditions = new LinkedList<>();
    private final List<RuleAction> actions = new LinkedList<>();

    public void addCondition(RuleCondition ruleCondition) {
        conditions.add(ruleCondition);
    }

    public void addAction(RuleAction ruleAction) {
        actions.add(ruleAction);
    }

    public List<RuleCondition> getConditions() {
        return conditions;
    }

    public List<RuleAction> getActions() {
        return actions;
    }
}

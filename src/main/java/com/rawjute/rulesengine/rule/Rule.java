package com.rawjute.rulesengine.rule;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Rule {

    private final String id;
    private String trigger;
    private final List<RuleCondition> conditions = new LinkedList<>();
    private final List<RuleAction> actions = new LinkedList<>();

    public Rule(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Optional<String> getTrigger() {
        return Optional.ofNullable(trigger);
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

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

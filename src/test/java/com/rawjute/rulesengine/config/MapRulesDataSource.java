package com.rawjute.rulesengine.config;

import com.rawjute.rulesengine.engine.RulesDataSource;
import com.rawjute.rulesengine.rule.Rule;

import java.util.LinkedList;
import java.util.List;

public class MapRulesDataSource implements RulesDataSource {

    private final List<Rule> rules = new LinkedList<>();


    @Override
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public List<Rule> getRules() {
        return new LinkedList<>(rules);
    }

    @Override
    public void removeRule(String ruleId) {
        rules.removeIf(r -> r.getId().equalsIgnoreCase(ruleId));
    }
}

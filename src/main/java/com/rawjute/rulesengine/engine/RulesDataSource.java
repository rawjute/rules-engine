package com.rawjute.rulesengine.engine;


import com.rawjute.rulesengine.rule.Rule;

import java.util.List;

public interface RulesDataSource {

    void addRule(Rule rule);

    List<Rule> getRules();

    void removeRule(String ruleId);

}

package com.rawjute.rulesengine.rule;

public interface ParameterizedRuleAction<T> extends RuleAction {

    T execute();

}

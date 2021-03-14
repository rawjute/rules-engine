package com.rawjute.rulesengine.engine;

import com.rawjute.rulesengine.rule.ParameterizedRuleAction;
import com.rawjute.rulesengine.rule.VoidRuleAction;
import com.rawjute.rulesengine.rule.RuleCondition;
import com.rawjute.rulesengine.rule.Rule;
import org.springframework.stereotype.Service;

@Service
public class RulesEngine {

    private final RulesDataSource rulesDataSource;

    public RulesEngine(RulesDataSource rulesDataSource) {
        this.rulesDataSource = rulesDataSource;
    }

    public RulesEngine removeRule(String ruleId) {
        rulesDataSource.removeRule(ruleId);
        return this;
    }

    public RulesEngine saveRule(Rule rule) {
        rulesDataSource.addRule(rule);
        return this;
    }

    void evaluateRules(String triggerName) {
        rulesDataSource.getRules().stream()
                .filter(e -> e.getTrigger().isEmpty() ||  e.getTrigger().get().equals(triggerName))
                .filter(r -> r.getConditions().stream().allMatch(RuleCondition::checkCondition))
                .forEach(r -> r.getActions().forEach(a -> {
                    if (a instanceof VoidRuleAction) {
                        ((VoidRuleAction) a).execute();
                    } else if (a instanceof ParameterizedRuleAction) {
                        ((ParameterizedRuleAction<?>) a).execute();
                    } else {
                        throw new IllegalStateException("Action type not supported");
                    }
                }));
    }

}

package com.rawjute.rulesengine.engine;

import com.rawjute.rulesengine.rule.ParameterizedRuleAction;
import com.rawjute.rulesengine.rule.VoidRuleAction;
import com.rawjute.rulesengine.rule.RuleCondition;
import com.rawjute.rulesengine.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RulesEngineManager {

    private static final Map<String, List<Rule>> rulesMap = new ConcurrentHashMap<>();

    public RulesEngineConditionManager when(String triggerName) {
        Rule rule = new Rule();
        if (!rulesMap.containsKey(triggerName)) {
            rulesMap.put(triggerName, new LinkedList<>());
        }
        rulesMap.get(triggerName).add(rule);
        return new RulesEngineConditionManager(this, rule);
    }

    void evaluateRules(String triggerName) {
        rulesMap.entrySet().stream()
                .filter(e -> e.getKey().equals(triggerName))
                .flatMap(e -> e.getValue().stream())
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

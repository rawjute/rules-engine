package com.rawjute.rulesengine.engine;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RulesEngineAspect {

    private final RulesEngine rulesEngine;

    public RulesEngineAspect(RulesEngine rulesEngine) {
        this.rulesEngine = rulesEngine;
    }

    @After("@annotation(trigger)")
    public void after(JoinPoint joinPoint, Trigger trigger) {
        rulesEngine.evaluateRules(trigger.value());
    }
}

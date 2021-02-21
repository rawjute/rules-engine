package com.rawjute.rulesengine.engine;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RulesEngineAspect {

    private final RulesEngineManager rulesEngineManager;

    public RulesEngineAspect(RulesEngineManager rulesEngineManager) {
        this.rulesEngineManager = rulesEngineManager;
    }

    @After("@annotation(trigger)")
    public void after(JoinPoint joinPoint, Trigger trigger) {
        rulesEngineManager.evaluateRules(trigger.value());
    }
}

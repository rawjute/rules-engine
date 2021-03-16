package com.rawjute.rulesengine.engine;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class RulesEngineAspect {

    private final List<RulesEngine> rulesEngineList;

    public RulesEngineAspect(List<RulesEngine> rulesEngineList) {
        this.rulesEngineList = rulesEngineList;
    }

    @After("@annotation(trigger)")
    public void after(JoinPoint joinPoint, Trigger trigger) {
        rulesEngineList.forEach(r -> r.evaluateRules(trigger.value()));
    }
}

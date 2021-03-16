package com.rawjute.rulesengine;

import com.rawjute.rulesengine.engine.AbstractRulesEngine;
import com.rawjute.rulesengine.engine.RulesDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RulesEngineImpl extends AbstractRulesEngine {

    public RulesEngineImpl(@Qualifier("MapRulesDataSource") RulesDataSource rulesDataSource) {
        super(rulesDataSource);
    }
}

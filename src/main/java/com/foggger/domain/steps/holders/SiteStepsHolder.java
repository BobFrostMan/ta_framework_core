package com.foggger.domain.steps.holders;

import com.foggger.core.config.Config;
import com.foggger.domain.steps.LoginSteps;
import com.foggger.domain.steps.imp.ProdLoginSteps;

public class SiteStepsHolder {

    private Config config;

    private LoginSteps loginSteps;

    public SiteStepsHolder(Config config) {
        this.config = config;
    }

    public boolean isCurrentProdVersion() {
        return config.get("app.env").equals("demo");
    }

    public LoginSteps loginSteps() {
        if (loginSteps == null) {
            loginSteps = isCurrentProdVersion() ? new ProdLoginSteps() : null;
        }
        return loginSteps;
    }

}

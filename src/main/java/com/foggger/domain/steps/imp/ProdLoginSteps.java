package com.foggger.domain.steps.imp;

import com.foggger.core.config.Config;
import com.foggger.core.config.ConfigProvider;
import com.foggger.domain.steps.LoginSteps;
import com.foggger.domain.ui.page.LoginPage;
import com.foggger.domain.ui.page.dashboard.DashboardPage;

public class ProdLoginSteps implements LoginSteps {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private Config config;

    public ProdLoginSteps() {
        config = ConfigProvider.provide();
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    }

    @Override
    public LoginSteps openSite() {
        loginPage.openSite();
        return this;
    }

    @Override
    public LoginSteps loginAs(String user, String password) {
        loginPage.loginInput.clear();
        loginPage.loginInput.sendKeys(user);
        loginPage.passwordInput.clear();
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginButton.click();
        return this;
    }

    @Override
    public boolean isUserLoggedIn() {
        String userName = config.get("data.username");
        return dashboardPage.headerMenu.isUserLoggedIn(userName);
    }
}

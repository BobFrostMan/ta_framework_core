package com.foggger.domain.ui.page;

import com.foggger.core.config.ConfigProvider;
import com.foggger.core.driver.decorator.CustomDecorator;
import com.foggger.core.driver.factory.DriverFactory;
import com.foggger.core.helper.ICanWait;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class AbstractPage implements ICanWait {

    protected RemoteWebDriver driver;

    public AbstractPage() {
        driver = DriverFactory.getDriver();
        PageFactory.initElements(new CustomDecorator(new DefaultElementLocatorFactory(driver)), this);
    }

    public void openSite() {
        driver.get(ConfigProvider.provide().get("app.url"));
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

}

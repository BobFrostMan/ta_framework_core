package com.foggger.domain.ui.popup;

import com.foggger.core.driver.decorator.CustomDecorator;
import com.foggger.core.driver.factory.DriverFactory;
import com.foggger.core.helper.ICanWait;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class AbstractPopup implements ICanWait {

    protected RemoteWebDriver driver;

    public AbstractPopup() {
        driver = DriverFactory.getDriver();
        PageFactory.initElements(new CustomDecorator(new DefaultElementLocatorFactory(driver)), this);
    }

    public abstract void waitForDisplayed();

    public abstract void waitForDisappear();

    protected void waitForElementAppear(WebElement el) {
        waitFor(el::isEnabled, true);
    }

    protected void waitForElementDisappear(WebElement el) {
        waitFor(() -> {
            try {
                return el.isEnabled();
            } catch (WebDriverException e) {
                return true;
            }
        }, true);
    }


}

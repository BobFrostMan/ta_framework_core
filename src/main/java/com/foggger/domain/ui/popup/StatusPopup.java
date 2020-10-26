package com.foggger.domain.ui.popup;

import com.foggger.core.driver.annotation.ElementName;
import com.foggger.core.driver.element.Button;
import com.foggger.core.driver.element.ClickableElement;
import com.foggger.core.logger.Logger;
import org.openqa.selenium.support.FindBy;

public class StatusPopup extends AbstractPopup {

    @ElementName("Status content element")
    @FindBy(xpath = "//*[contains(@id,'cs-alert-container')]//div[contains(@class, 'alert-content')]")
    private ClickableElement statusMessage;

    @ElementName("Close status popup")
    @FindBy(xpath = "//*[contains(@id,'cs-alert-container')]//button")
    private Button closeButton;

    @Override
    public void waitForDisplayed() {
        Logger.info("Wait for status popup to be displayed...");
        waitForElementAppear(statusMessage);
        waitFor(() -> statusMessage.isEnabled(), true);
    }

    @Override
    public void waitForDisappear() {
        Logger.info("Wait for status popup to disappear...");
        waitForElementDisappear(statusMessage);
    }

    public void close() {
        closeButton.click();
    }

}

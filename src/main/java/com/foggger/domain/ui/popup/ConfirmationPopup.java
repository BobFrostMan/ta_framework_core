package com.foggger.domain.ui.popup;

import com.foggger.core.driver.annotation.ElementName;
import com.foggger.core.driver.element.Button;
import com.foggger.core.driver.element.ClickableElement;
import com.foggger.core.logger.Logger;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPopup extends AbstractPopup {

    @ElementName("Popup title")
    @FindBy(xpath = "//*[contains(@class, 'ui-dialog-title')]//span[contains(@class, 'ui-dialog-title')]")
    public ClickableElement title;

    @ElementName("Close popup button")
    @FindBy(xpath = "//*[contains(@class, 'ui-dialog-title')]//button[@role='button']")
    public Button closePopupButton;

    @ElementName("Popup Ok button")
    @FindBy(xpath = "//div[contains(@class, 'ui-dialog-buttonset')]//button//*[contains(text(), 'Ok')]")
    public Button okButton;

    @ElementName("Popup Cancel button")
    @FindBy(xpath = "//div[contains(@class, 'ui-dialog-buttonset')]//button//*[contains(text(), 'Cancel')]")
    public Button cancelButton;

    @Override
    public void waitForDisplayed() {
        Logger.info("Wait for Confirmation popup to be displayed...");
        waitForElementAppear(title);
    }

    @Override
    public void waitForDisappear() {
        Logger.info("Wait for Confirmation popup to disappear...");
        waitForElementDisappear(title);
    }
}

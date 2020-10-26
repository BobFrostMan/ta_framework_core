package com.foggger.domain.ui.component.header;

import com.foggger.core.driver.annotation.ElementName;
import com.foggger.core.driver.element.ClickableElement;
import com.foggger.core.driver.element.Dropdown;
import com.foggger.core.logger.Logger;
import com.foggger.domain.ui.component.AbstractComponent;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;

public class HeaderMenu extends AbstractComponent {

    //User profile dropdown
    @ElementName("User profile dropdown")
    @FindBy(id = "top-user-dropdown")
    public ClickableElement userProfileDropdown;

    @ElementName("User name element")
    @FindBy(xpath = "//*[@id='top-user-dropdown']//span")
    public ClickableElement userNameSpan;

    //Menu items
    @ElementName("Dashboard")
    @FindBy(xpath = "//*[@id='cs-main-menu']//li//*[contains(text(), 'Dashboard')]")
    public ClickableElement dashboardMenuItem;

    @ElementName("Programs")
    @FindBy(xpath = "//*[@id='cs-main-menu']//li//*[contains(text(), 'Programs')]")
    public ClickableElement programsMenuItem;

    @ElementName("Patient Lists")
    @FindBy(xpath = "//*[@id='cs-main-menu']//li//*[contains(text(), 'Patient Lists')]")
    public ClickableElement patientListsMenuItem;

    @ElementName("Staff")
    @FindBy(xpath = "//*[@id='cs-main-menu']//li//*[contains(text(), 'Staff')]")
    public ClickableElement staffMenuItem;

    @ElementName("Campaigns")
    @FindBy(xpath = "//*[@id='cs-main-menu']//li//*[contains(text(), 'Campaigns')]")
    public ClickableElement campaignsMenuItem;

    @ElementName("Messages")
    @FindBy(xpath = "//*[@id='cs-main-menu']//li//*[contains(text(), 'Messages')]")
    public ClickableElement messagesMenuItem;

    @ElementName("Reports dropdown")
    @FindBy(xpath = "//*[@id='cs-main-menu']//li/*[contains(text(), 'Reports')]/..")
    public Dropdown reportsDropdown;

    public HeaderMenu() {
    }

    public boolean isUserLoggedIn(String username) {
        try {
            return userNameSpan.getText().contains(username);
        } catch (WebDriverException ex) {
            Logger.error("Cannot get username", ex);
            return false;
        }
    }
}

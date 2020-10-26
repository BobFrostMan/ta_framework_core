package com.foggger.domain.ui.page;

import com.foggger.core.driver.annotation.ElementName;
import com.foggger.core.driver.element.Button;
import com.foggger.core.driver.element.Input;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @ElementName("Login input")
    @FindBy(id = "username")
    public Input loginInput;

    @ElementName("Password input")
    @FindBy(id = "password")
    public Input passwordInput;

    @ElementName("Log In button")
    @FindBy(tagName = "button")
    public Button loginButton;

}

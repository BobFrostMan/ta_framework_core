package com.foggger.core.driver.element;

import org.apache.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Input extends ClickableElement {

    private static final String LOG_NAME = Input.class.getCanonicalName();

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ClickableElement.class);

    public Input(WebElement element) {
        super(element);
    }

    public Input(Object element, String name) {
        super(element, name);
    }

    public Input(Object element, String name, By locator) {
        super(element, name, locator);
    }

    public Input(Object element) {
        super(element);
    }

    public void enterText(String text) {
        if (text != null) {
            LOG.log(LOG_NAME, Level.DEBUG, "Clear " + name, null);
            highlight();
            innerElement.clear();
            sendKeys(text);
        } else {
            LOG.log(LOG_NAME, Level.INFO, "Provided text is null, nothing to enter to " + name, null);
        }
    }
}

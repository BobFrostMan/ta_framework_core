package com.foggger.core.driver.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button extends ClickableElement {

    public Button(WebElement element) {
        super(element);
    }

    public Button(Object element, String name) {
        super(element, name);
    }

    public Button(Object element, String name, By locator) {
        super(element, name, locator);
    }

    public Button(Object element) {
        super(element);
    }
}

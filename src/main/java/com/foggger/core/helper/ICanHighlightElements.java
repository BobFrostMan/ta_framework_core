package com.foggger.core.helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Interface that provides WebElement highlight functionality
 */
public interface ICanHighlightElements {

    /**
     * Highlights provided element for some time
     *
     * @param driver  - web driver to be used as JSExecutor
     * @param element - webElement to highlight
     */
    default void highlightElement(WebDriver driver, WebElement element) {
        try {
            String highlightScript = "element = arguments[0];" +
                    "original_style = element.getAttribute('style');" +
                    "element.setAttribute('style', original_style + '; border: 2px solid yellow;');" +
                    "setTimeout(function(){element.setAttribute('style', original_style);}, 300);";
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript(highlightScript, element);
        } catch (Throwable t) {
            //do nothing, it is not a critical action
        }
    }
}

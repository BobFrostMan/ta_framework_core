package com.foggger.core.driver.element;

import org.apache.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Dropdown extends ClickableElement {

    private static final String LOG_NAME = Dropdown.class.getCanonicalName();

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Dropdown.class);

    private static final String DROPDOWN_UL_XPATH = ".//ul";

    private static final String DROPDOWN_VALUE_XPATH = ".//li//*[contains(text(),'%s')]";
    private static final String SELECT_OPTION_XPATH = ".//option[contains(text(),'%s')]";

    private static final String OPTIONS_XPATH = ".//option";
    private static final String LIST_ITEMS_XPATH = ".//li//*";

    public Dropdown(WebElement element) {
        super(element);
    }

    public Dropdown(Object element, String name) {
        super(element, name);
    }

    public Dropdown(Object element, String name, By locator) {
        super(element, name, locator);
    }

    public Dropdown(Object element) {
        super(element);
    }

    public void select(String value) {
        if ("select".equals(getTagName())) {
            selectInSelectElement(value);
        } else {
            selectInDropdownElement(this, value);
        }
    }

    /**
     * Some dropdowns, are quite complicated so you need to click on some "expand"
     * element before you see the dropdown, sometimes it is completely different DOM elements.
     * This method uses expandElement to click and then selects value from provided dropdown.
     *
     * @param expandElement - element to click first.
     * @param value         - value to be selected from dropdown.
     */
    public void select(ClickableElement expandElement, String value) {
        selectInDropdownElement(expandElement, value);
    }

    private void selectInSelectElement(String value) {
        LOG.log(LOG_NAME, Level.INFO, "Select '" + value + "' from " + name, null);
        innerElement.click();
        WebElement option = innerElement.findElement(By.xpath(String.format(SELECT_OPTION_XPATH, value)));
        highlight(option);
        option.click();
    }

    private void selectInDropdownElement(ClickableElement expandElement, String value) {
        LOG.log(LOG_NAME, Level.INFO, "Select '" + value + "' from " + name, null);
        expandElement.innerElement.click();
        waitFor(() -> driver.findElement(By.xpath(DROPDOWN_UL_XPATH)).isDisplayed(), 5, false);
        WebElement option = innerElement.findElement(By.xpath(String.format(DROPDOWN_VALUE_XPATH, value)));
        highlight(option);
        option.click();
    }

    public List<String> getAvailableOptions() {
        LOG.log(LOG_NAME, Level.INFO, "Retrieve all available options from " + name, null);
        highlight(innerElement);
        innerElement.click();
        List<WebElement> options;
        if ("select".equals(getTagName())) {
            options = innerElement.findElements(By.xpath(OPTIONS_XPATH));
        } else {
            options = innerElement.findElements(By.xpath(LIST_ITEMS_XPATH));
        }
        List<String> res = new ArrayList<>();
        for (WebElement option : options) {
            highlight(option);
            res.add(option.getText().trim());
        }
        return res;
    }

    public List<String> getAvailableOptions(ClickableElement expandElement) {
        LOG.log(LOG_NAME, Level.INFO, "Retrieve all available options from " + name, null);
        expandElement.innerElement.click();
        List<WebElement> options;
        if ("select".equals(getTagName())) {
            options = expandElement.findElements(By.xpath(OPTIONS_XPATH));
        } else {
            options = expandElement.findElements(By.xpath(LIST_ITEMS_XPATH));
        }
        List<String> res = new ArrayList<>();
        for (WebElement option : options) {
            highlight(option);
            res.add(option.getText().trim());
        }
        return res;
    }


}

package com.foggger.domain.ui.component.search;

import com.foggger.core.driver.annotation.ElementName;
import com.foggger.core.driver.element.Button;
import com.foggger.core.driver.element.ClickableElement;
import com.foggger.core.driver.element.Input;
import com.foggger.core.logger.Logger;
import com.foggger.domain.ui.component.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchWithSelection extends AbstractComponent {

    private static final String VALUE_XPATH = "//*[@class='bs-searchbox']/input/../../ul/li//*[contains(text(), '%s')]";

    @ElementName("Client input expand")
    @FindBy(xpath = "//*[@data-id='cs-client-select']")
    private Button expandElement;

    @ElementName("Search input")
    @FindBy(xpath = "//*[@class='bs-searchbox']/input")
    private Input searchInput;

    public void search(String text) {
        if (expandElement.getText().equals(text)) {
            Logger.info("Value '" + text + "' already selected in search dropdown. No actions required");
            return;
        }
        Logger.info("Searching item by text '" + text + "'...");
        String url = driver.getCurrentUrl();
        expandElement.click();
        waitFor(() -> searchInput.isVisible(), false);
        searchInput.sendKeys(text);
        WebElement option = driver.findElement(By.xpath(String.format(VALUE_XPATH, text)));
        new ClickableElement(option, text + " option item").click();
        waitFor(() -> !driver.getCurrentUrl().equals(url), false);
    }

}

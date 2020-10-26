package com.foggger.domain.ui.component.table.base;

import com.foggger.core.driver.annotation.ElementName;
import com.foggger.core.driver.element.Input;
import com.foggger.domain.ui.component.AbstractComponent;
import org.openqa.selenium.support.FindBy;

class TableSearch extends AbstractComponent {

    @ElementName("Table search input")
    @FindBy(xpath = ".//input[@type='search']")
    private Input searchInput;

    public void search(String text) {
        searchInput.sendKeys(text);
    }


}

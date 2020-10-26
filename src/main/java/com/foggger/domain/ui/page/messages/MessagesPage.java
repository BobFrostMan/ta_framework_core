package com.foggger.domain.ui.page.messages;

import com.foggger.core.driver.annotation.ElementName;
import com.foggger.core.driver.element.Button;
import com.foggger.domain.ui.component.search.SearchWithSelection;
import com.foggger.domain.ui.page.AbstractPage;
import com.foggger.domain.ui.popup.SelectModuleActionTypePopup;
import org.openqa.selenium.support.FindBy;

public class MessagesPage extends AbstractPage {

    public SearchWithSelection searchClient;
    public SelectModuleActionTypePopup selectModuleActionTypePopup;

    @ElementName("Add message button")
    @FindBy(id = "messageAddBtn")
    public Button addButton;

    public MessagesPage() {
        searchClient = new SearchWithSelection();
        selectModuleActionTypePopup = new SelectModuleActionTypePopup();
    }

}

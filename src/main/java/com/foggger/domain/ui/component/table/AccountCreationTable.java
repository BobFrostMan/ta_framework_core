package com.foggger.domain.ui.component.table;

import com.foggger.domain.ui.component.table.base.ItemsTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountCreationTable extends ItemsTable {

    public AccountCreationTable(By tableBodyLocator) {
        super(tableBodyLocator);
    }

    public QuestionRowItem findItemByField(String value) {
        return new QuestionRowItem(getRowWebElementBy("Field", value));
    }

    public QuestionRowItem getLastQuestionRowItem() {
        List<WebElement> rowsList = getRowsWebElements();
        //avoid 0-element - it's header
        //avoid last element - it's the question row template
        // So last question row index is size() - 2
        return new QuestionRowItem(rowsList.get(rowsList.size() - 2));
    }

    public int getAddedQuestionsCount() {
        List<WebElement> rowsList = getRowsWebElements();
        // for add creation question row, there is one hidden ac-template-row, that should not be counted
        return rowsList.size() <= 2 ? 0 : rowsList.size() - 2;
    }

}

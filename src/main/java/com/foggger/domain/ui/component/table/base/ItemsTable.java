package com.foggger.domain.ui.component.table.base;

import com.foggger.core.driver.element.ClickableElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ItemsTable {

    protected TableSearch tableSearch;
    protected TableBody tableBody;
    protected TableFooter tableFooter;

    public ItemsTable(By tableBodyLocator) {
        tableSearch = new TableSearch();
        tableBody = new TableBody(tableBodyLocator);
        tableFooter = new TableFooter();
    }

    /**
     * Find table row and by value from columnName header
     *
     * @param columnName - table header
     * @param tableValue - value to search in column
     * @return TableItem
     */
    public TableRowItem searchInTable(String columnName, String tableValue) {
        //TODO: split this part to search filter and search
        tableSearch.search(tableValue);
        return tableBody.findItemBy(columnName, tableValue);
    }

    public TableRowItem getFirstRowItem() {
        return tableBody.getFirstRowItem();
    }

    public ClickableElement editFirstItemButton() {
        return tableBody.editFirstRecordButton();
    }

    public ClickableElement removeFirstItemButton() {
        return tableBody.deleteFirstRecordButton();
    }

    protected WebElement getRowWebElementBy(String header, String value) {
        return tableBody.findRowWebElementBy(header, value);
    }

    protected List<WebElement> getRowsWebElements() {
        return tableBody.getRowsWebElelments();
    }


}

package com.foggger.domain.ui.component.table;

import com.foggger.core.driver.element.Button;
import com.foggger.core.driver.element.CheckBox;
import com.foggger.core.driver.element.Dropdown;
import com.foggger.core.driver.element.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class QuestionRowItem {

    private WebElement rowWebElement;

    public QuestionRowItem(WebElement rowWebElement) {
        this.rowWebElement = rowWebElement;
    }

    public CheckBox mandatoryQuestionCheckbox() {
        WebElement element = rowWebElement.findElement(By.xpath(".//input[@type='checkbox']"));
        return new CheckBox(element, "Mandatory Question checkbox");
    }

    public Dropdown fieldDropdown() {
        WebElement element = rowWebElement.findElement(By.xpath(".//select"));
        return new Dropdown(element, "Field dropdown");
    }

    public Input questionTextInput() {
        WebElement element = rowWebElement.findElement(By.name("ac-question"));
        return new Input(element, "Question text input");
    }

    public Input onErrorTextInput() {
        WebElement element = rowWebElement.findElement(By.name("ac-error"));
        return new Input(element, "On Error text input");
    }

    private Button removeQuestionButton() {
        WebElement element = rowWebElement.findElement(By.xpath(".//button"));
        return new Button(element, "Remove question button");
    }

}

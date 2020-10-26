package com.foggger.core.driver.element;

import com.foggger.core.config.Config;
import com.foggger.core.config.ConfigProvider;
import com.foggger.core.driver.factory.DriverFactory;
import com.foggger.core.helper.ICanHighlightElements;
import com.foggger.core.helper.ICanWait;
import org.apache.log4j.Level;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Custom web element wrapper, can be used as a simple selenium WebElement.
 */
public class ClickableElement implements WebElement, Locatable, WrapsElement, ICanWait, ICanHighlightElements {

    private static final String LOG_NAME = ClickableElement.class.getCanonicalName();

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ClickableElement.class);

    protected WebElement innerElement;
    protected Config config;
    protected RemoteWebDriver driver;

    public By locator;
    public String name;

    public ClickableElement(WebElement element) {
        config = ConfigProvider.provide();
        driver = DriverFactory.getDriver();
        this.innerElement = element;
    }

    public ClickableElement(Object element, String name) {
        config = ConfigProvider.provide();
        driver = DriverFactory.getDriver();
        this.innerElement = (WebElement) element;
        this.name = name;
    }

    public ClickableElement(Object element, String name, By locator) {
        config = ConfigProvider.provide();
        driver = DriverFactory.getDriver();
        this.innerElement = (WebElement) element;
        this.name = name;
        this.locator = locator;
    }

    public ClickableElement(Object element) {
        config = ConfigProvider.provide();
        driver = DriverFactory.getDriver();
        this.innerElement = (WebElement) element;
    }

    public ClickableElement refreshElement() {
        this.innerElement = DriverFactory.getDriver().findElement(locator);
        return this;
    }

    @Override
    public void click() {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(innerElement));
        highlight();
        try {
            LOG.log(LOG_NAME, Level.INFO, "Click on " + name, null);
            Actions actions = new Actions(DriverFactory.getDriver());
            actions.moveToElement(innerElement).click().build().perform();
        } catch (Throwable ex) {
            LOG.log(LOG_NAME, Level.ERROR, name + " was not able to be clicked.", ex);
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void submit() {
        highlight();
        LOG.log(LOG_NAME, Level.DEBUG, "Submit " + name, null);
        innerElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        highlight();
        if (name != null && name.toLowerCase().contains("password")) {
            LOG.log(LOG_NAME, Level.INFO, "Typing text '*********' to " + name, null);
        } else {
            StringBuilder builder = new StringBuilder();
            for (CharSequence ch : charSequences) {
                builder.append(ch);
            }
            LOG.log(LOG_NAME, Level.INFO, "Typing text '" + builder.toString() + "' to " + name, null);
        }
        innerElement.sendKeys(charSequences);
    }

    @Override
    public void clear() {
        LOG.log(LOG_NAME, Level.INFO, "Clear " + name, null);
        highlight();
        innerElement.clear();
    }

    @Override
    public String getTagName() {
        return innerElement.getTagName();
    }

    @Override
    public String getAttribute(String attribute) {
        return innerElement.getAttribute(attribute);
    }

    @Override
    public boolean isSelected() {
        highlight();
        return innerElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        highlight();
        return innerElement.isEnabled();
    }

    @Override
    public String getText() {
        highlight();
        return innerElement.getText().trim();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return innerElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return innerElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 3);
            wait.until(ExpectedConditions.visibilityOf(innerElement));
            highlight();
            return innerElement.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Override
    public Point getLocation() {
        return innerElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return innerElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return innerElement.getRect();
    }

    @Override
    public String getCssValue(String value) {
        return innerElement.getCssValue(value);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return innerElement.getScreenshotAs(outputType);
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) innerElement).getCoordinates();
    }

    @Override
    public WebElement getWrappedElement() {
        return innerElement;
    }

    public void scrollIntoView() {
        try {
            waitFor(() -> {
                ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView({block : \"center\"});", innerElement);
                TimeUnit.MILLISECONDS.sleep(500);
                return isVisible();
            }, 30, false);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public Boolean isVisible() {
        return (Boolean) ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , innerElement);
    }

    protected void highlight() {
        highlight(this.innerElement);
    }

    protected void highlight(WebElement element) {
        boolean isHighlightRequired = Boolean.parseBoolean(config.get("driver.highlighting"));
        if (isHighlightRequired) {
            highlightElement(DriverFactory.getDriver(), element);
        }
    }
}

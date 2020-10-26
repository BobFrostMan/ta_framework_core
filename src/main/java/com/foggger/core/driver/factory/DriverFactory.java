package com.foggger.core.driver.factory;

import com.foggger.core.config.ConfigProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * DriverFactory can provide different webdriver instances based on configurations
 */
public class DriverFactory {

    //Thread safe storage for WebDrivers in case if code parallelism need
    private static ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal<>();

    /**
     * Returns RemoteWebDriver instance based on command line arguments
     * or property file configuration for current thread.
     *
     * @return RemoteWebDriver instance for current thread.
     */
    public static RemoteWebDriver getDriver() {
        if (drivers.get() == null) {
            String hubUrl = ConfigProvider.provide().get("driver.hub");
            String driverName = ConfigProvider.provide().get("driver.name");
            String driverVersion = ConfigProvider.provide().get("driver.version");
            RemoteWebDriver driver;
            if (hubUrl != null && !hubUrl.isEmpty()) {
                driver = createRemoteWebDriver(driverName, hubUrl);
            } else {
                driver = createLocalWebDriver(driverName, driverVersion);
            }
            drivers.set(driver);
        }
        return drivers.get();
    }

    /**
     * Setup webdriver binary and creates webdriver instance for provided driver name.
     *
     * @param driverName    - name of required web driver.
     * @param driverVersion - version of required webdriver. If null then latest version will be used.
     * @return RemoteWebDriver instance.
     */
    private static RemoteWebDriver createLocalWebDriver(String driverName, String driverVersion) {
        DriverManagerType driverType = getType(driverName);
        if (driverVersion != null && !driverVersion.isEmpty()) {
            WebDriverManager.getInstance(driverType).driverVersion(driverVersion).setup();
        } else {
            WebDriverManager.getInstance(driverType).setup();
        }
        RemoteWebDriver remoteWebDriver;
        switch (driverType) {
            case CHROME:
                remoteWebDriver = new ChromeDriver();
                break;
            case EDGE:
                remoteWebDriver = new EdgeDriver();
                break;
            default:
                throw new UnsupportedOperationException("Driver creation is not supported for type " + driverName);
        }
        remoteWebDriver.manage().window().maximize();
        return remoteWebDriver;
    }

    /**
     * Creates webdriver instance using selenium grid.
     *
     * @param driverName - name of required web driver.
     * @param hubUrl     - selenium hub url to interact with.
     * @return RemoteWebDriver instance.
     */
    private static RemoteWebDriver createRemoteWebDriver(String driverName, String hubUrl) {
        throw new UnsupportedOperationException("Remote web driver operation creation is not supported yet");
    }

    /**
     * Returns DriverManagerType for provided webdriver name.
     *
     * @param driverName - webdriver name.
     * @return DriverManagerType instance for provided webdriver name.
     */
    private static DriverManagerType getType(String driverName) {
        String driver = driverName.toLowerCase();
        if (driver.contains("chrome")) {
            return DriverManagerType.CHROME;
        }
        if (driver.contains("edge")) {
            return DriverManagerType.EDGE;
        }
        if (driver.contains("firefox")) {
            return DriverManagerType.FIREFOX;
        }
        if (driver.contains("safari")) {
            return DriverManagerType.SAFARI;
        }
        throw new IllegalArgumentException("Driver type is not recognized for " + driverName);
    }

}

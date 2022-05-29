package com.gmail.kudryavtseva.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Webdriver Singleton class.
 */
public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() {}

    /**
     * This method returns an existing driver or creates a new one if it didn't exist.
     *
     * @return a Webdrive instance for a given browser.
     */
    public static WebDriver getDriver() {
        if (null == driver) {
            switch (System.getProperty("browser")) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
            }
            driver.manage().window().maximize();
        }

        return driver;
    }

    /**
     * This method shuts down the driver.
     */
    public static void closeDriver() {
        driver.quit();
        driver = null;
    }

}

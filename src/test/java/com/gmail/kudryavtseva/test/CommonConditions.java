package com.gmail.kudryavtseva.test;

import com.gmail.kudryavtseva.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


/**
 * This class contains methods and properties to be re-used by multiple tests.
 */
public class CommonConditions {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

}

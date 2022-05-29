package com.gmail.kudryavtseva.page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    protected abstract AbstractPage openPage();

    protected abstract AbstractPage acceptCookies();

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

}

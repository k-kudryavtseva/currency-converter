package com.gmail.kudryavtseva.page;

import com.gmail.kudryavtseva.util.SleepUtils;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends AbstractPage {

    static Logger logger = LogManager.getRootLogger();

    private final String BASE_URL = "https://www.xe.com/";

    private final By acceptCookiesPopupLocator = By.cssSelector("[class*=\"ConsentBanner___StyledLargeContainer\"]");

    private final By acceptCookiesButtonLocator = By.cssSelector("[class*=\"ConsentBanner\"] [class=\"button__BaseButton-sc-1qpsalo-0 ctapkr\"]");

    private final By amountInputFieldLocator = By.id("amount");

    private final By fromCurrencyFieldLocator = By.id("midmarketFromCurrency");

    @FindBy(id = "midmarketFromCurrency")
    private WebElement fromCurrencyField;

    private final By toCurrencyFieldLocator = By.id("midmarketToCurrency");

    @FindBy(id = "midmarketToCurrency")
    private WebElement toCurrencyField;

    @FindBy(css = "[type=\"submit\"]")
    private WebElement convertButton;

    public MainPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("Open main page step")
    @Override
    public MainPage openPage() {

        driver.navigate().to(BASE_URL);

        return this;
    }

    @Step("Accept Cookies step")
    @Override
    public MainPage acceptCookies() {

        WebElement acceptCookiesButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(acceptCookiesButtonLocator));
        acceptCookiesButton.click();
        logger.info("Accept cookies button was clicked");

        return this;
    }

    @Step("Enter amount of money should be converted step")
    public MainPage enterAmount(String amount) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(acceptCookiesPopupLocator));
        driver.findElement(amountInputFieldLocator).sendKeys(amount);
        logger.info("Money amount: " + amount);

        return this;
    }

    @Step("Choose from currency step")
    public MainPage chooseFromCurrency(String fromCurrency) {
        fromCurrencyField = driver.findElement(fromCurrencyFieldLocator);
        fromCurrencyField.click();
        fromCurrencyField.sendKeys(fromCurrency, Keys.RETURN);
        logger.info("From currency: " + fromCurrency);

        SleepUtils.sleep(2000);

        return this;
    }

    @Step("Choose to currency step")
    public MainPage chooseToCurrency(String toCurrency) {
        toCurrencyField = driver.findElement(toCurrencyFieldLocator);
        toCurrencyField.click();
        toCurrencyField.sendKeys(toCurrency, Keys.RETURN);
        logger.info("To currency: " + toCurrency);

        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(convertButton));

        SleepUtils.sleep(2000);

        return this;
    }

    @Step("Click Convert button step")
    public CurrencyConverterPage clickConvertButton() {
        convertButton.click();
        logger.info("Convert button was clicked");
        SleepUtils.sleep(2000);

        return new CurrencyConverterPage(driver);
    }

}

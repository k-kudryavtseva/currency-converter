package com.gmail.kudryavtseva.page;

import com.gmail.kudryavtseva.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CurrencyConverterPage extends MainPage {

    private final String BASE_URL = "https://www.xe.com/currencyconverter/";

    private final By convertedMoneyAmountLocator = By.cssSelector("[class*=\"result__BigRate\"]");

    @Override
    public CurrencyConverterPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public CurrencyConverterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public Double getConvertedAmount() {
        WebElement convertedMoneyAmount = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(convertedMoneyAmountLocator));

        return Double.parseDouble(StringUtils.getMoneyAmountWithoutCurrencyName(convertedMoneyAmount.getText()));
    }


}

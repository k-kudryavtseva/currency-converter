package com.gmail.kudryavtseva.test;

import com.gmail.kudryavtseva.page.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(io.qameta.allure.testng.AllureTestNg.class)
public class CurrencyConverterTest extends CommonConditions {

    @DataProvider(name = "currency-converter-test-data-provider")
    public Object[][] getCurrencyConverterTestData() {
        return new Object[][]{
                {"100", "EUR", "GBP", 84.99, 1e-2},
                {"1", "EUR", "GBP", 0.8499, 1e-3},
                {"1.0", "EUR", "EUR", 1.0, 0.0},
                {"0.01", "USD", "EUR", 0.009, 1e-3},
                {"999999.99", "USD", "EUR", 931573.60, 50.0},
        };
    }

    @Epic("Acceptance tests")
    @Feature("Currency conversion tests")
    @Test (dataProvider = "currency-converter-test-data-provider")
    @Description("Iterating over 5 sets of sample data")
    public void convertCurrency(String amount, String fromCurrency, String toCurrency, Double expectedAmount, Double acceptedDelta) {

        Double convertedAmount = new MainPage(driver)
                .openPage()
                .acceptCookies()
                .enterAmount(amount)
                .chooseFromCurrency(fromCurrency)
                .chooseToCurrency(toCurrency)
                .clickConvertButton()
                .getConvertedAmount();

        Assert.assertEquals(convertedAmount, expectedAmount, acceptedDelta);

    }

}

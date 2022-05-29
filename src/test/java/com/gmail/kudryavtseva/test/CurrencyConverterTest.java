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
                // basic test cases
                {"100", "EUR", "GBP", 84.99, 1.0},
                {"1", "EUR", "GBP", 0.8499, 0.1},
                // test that decimal numbers are processed as expected,
                // delta should be zero here because we convert the same currency,
                // so we expect exactly 1 as a result
                {"1.0", "EUR", "EUR", 1.0, 0.0},
                // test that the smallest possible amount works as expected
                {"0.01", "USD", "EUR", 0.009, 1e-3},
                // test a large number
                // which produces numbers containing commas
                {"999999.99", "USD", "EUR", 931573.60, 1000.0},
        };
    }

    /**
     * This test is responsible to test that currency conversion produces a reasonable result.
     * Note: we don't test exact conversion rates because they tend to change
     * so we only verify that the obtained values are somewhat reasonable
     * that's why we use pretty large deltas for comparison.
     *
     * @param amount the amount of money to be converted
     * @param fromCurrency a currency to convert money from
     * @param toCurrency a currency to convert money to
     * @param expectedAmount an expected conversion result (rough)
     * @param acceptedDelta a delta to indicate an interval where we accept the converted amount
     *                      (we use pretty wide range as we don't test exact rates here)
     */
    @Epic("Acceptance tests")
    @Feature("Currency conversion tests")
    @Test (dataProvider = "currency-converter-test-data-provider")
    @Description("Iterating over sets of sample data")
    public void convertCurrency(
            String amount,
            String fromCurrency,
            String toCurrency,
            Double expectedAmount,
            Double acceptedDelta
    ) {

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

package com.gmail.kudryavtseva.util;


/**
 * This class contains utils to parse string represented numbers into numeric values.
 */
public class StringUtils {

    /**
     * This method converts a string representation of a number obtained from a converter to a double value.
     *
     * @param inputString a resulting output from the converter
     * @return a double value for a given amount.
     */
    public static String getMoneyAmountWithoutCurrencyName(String inputString) {

        String[] splited = inputString
                .replaceAll(",", "") // large numbers have commas in the converter representation
                .split("\\s+"); // a number is followed by currency name/code, so we take the numeric value only
        return splited[0];

    }

}

package com.gmail.kudryavtseva.util;

public class StringUtils {

    public static String getMoneyAmountWithoutCurrencyName(String inputString) {

        String[] splited = inputString.replaceAll(",", "").split("\\s+");
        return splited[0];

    }

}

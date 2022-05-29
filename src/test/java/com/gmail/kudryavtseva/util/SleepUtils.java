package com.gmail.kudryavtseva.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * This class contains utils to performed fixed sleeps.
 */
public class SleepUtils {

    static Logger logger = LogManager.getRootLogger();

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            logger.error(e);
        }
    }

}

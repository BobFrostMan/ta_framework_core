package com.foggger.core.helper;

import com.foggger.core.logger.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Provides an opportunity to wait for any condition, described as Callable function or lambda.
 */
public interface ICanWait {
    /**
     * Waits for function to return true with fixed timeout - 30 seconds.
     * Returns result of last function execution.
     *
     * @param func - wait function to execute
     * @return true if wait was successful
     */
    default Boolean waitFor(Callable<Boolean> func) {
        return waitFor(func, 30);
    }

    /**
     * Waits for function to return true with timeout.
     * Returns result of last function execution.
     *
     * @param func       - wait function to execute
     * @param timeOutSec - execution timeout (in seconds)
     * @return true if wait was successful
     */
    default Boolean waitFor(Callable<Boolean> func, int timeOutSec) {
        return waitFor(func, timeOutSec, true);
    }

    /**
     * Waits for function to return true with timeout.
     * Returns result of last function execution.
     * You can enable wait results logging by providing enableLogging = true
     *
     * @param func          - wait function to execute
     * @param enableLogging - enable wait results logging, true to enable, false to disable
     * @return true if wait was successful
     */
    default Boolean waitFor(Callable<Boolean> func, boolean enableLogging) {
        return waitFor(func, 30, enableLogging);
    }

    /**
     * Waits for function to return true with timeout.
     * Returns result of last function execution.
     * You can enable wait results logging by providing enableLogging = true
     *
     * @param func          - wait function to execute
     * @param timeOutSec    - execution timeout (in seconds)
     * @param enableLogging - enable wait results logging, true to enable, false to disable
     * @return true if wait was successful
     */
    default Boolean waitFor(Callable<Boolean> func, int timeOutSec, boolean enableLogging) {
        if (enableLogging) {
            Logger.info("Waiting for condition to be true (timeout " + timeOutSec + " seconds)..");
        }
        long startTime = System.currentTimeMillis();
        long endTime = startTime + TimeUnit.SECONDS.toMillis(timeOutSec);
        while (System.currentTimeMillis() <= endTime) {
            try {
                Boolean res = func.call();
                if (res) {
                    if (enableLogging) {
                        Logger.info("Wait condition successfully finished!");
                    }
                    return res;
                }
            } catch (Throwable e) {
                //do nothing
            }
        }
        if (enableLogging) {
            Logger.info("Wait condition still false after " + timeOutSec + " seconds!");
        }
        return false;
    }
}
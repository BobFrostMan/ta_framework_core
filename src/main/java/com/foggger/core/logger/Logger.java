package com.foggger.core.logger;


import org.apache.log4j.Level;

/**
 * Static logger class to avoid new logger instance creation for each class that need logging functionality
 */
public class Logger {

    private static final String LOGGER_NAME = Logger.class.getCanonicalName();
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Logger.class);

    public static void fatal(final String message) {
        LOG.log(LOGGER_NAME, Level.FATAL, message, null);
    }

    public static void fatal(final String message, final Throwable t) {
        LOG.log(LOGGER_NAME, Level.FATAL, message, t);
    }

    public static void error(final String message) {
        LOG.log(LOGGER_NAME, Level.ERROR, message, null);
    }

    public static void error(final String message, final Throwable t) {
        LOG.log(LOGGER_NAME, Level.ERROR, message, t);
    }

    public static void info(final String message, final Throwable t) {
        LOG.log(LOGGER_NAME, Level.INFO, message, t);
    }

    public static void info(final String message) {
        LOG.log(LOGGER_NAME, Level.INFO, message, null);
    }

    public static void warn(final String message) {
        LOG.log(LOGGER_NAME, Level.WARN, message, null);
    }

    public static void warn(final String message, final Throwable t) {
        LOG.log(LOGGER_NAME, Level.WARN, message, t);
    }

    public static void debug(final String message) {
        LOG.log(LOGGER_NAME, Level.DEBUG, message, null);
    }

    public static void debug(final String message, final Throwable t) {
        LOG.log(LOGGER_NAME, Level.DEBUG, message, t);
    }

}

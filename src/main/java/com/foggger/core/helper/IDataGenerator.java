package com.foggger.core.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;

public interface IDataGenerator {

    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String nonZeroDigits = "123456789";
    String withZeroDigits = "0" + nonZeroDigits;
    String allCharacters = letters + letters.toLowerCase() + withZeroDigits;
    String dd_MM_yy_H_mm = "dd-MM-yy H:mm";
    String MM_dd_yyyy = "MM/dd/yyyy";
    String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * Returns date as formatted string with default format
     *
     * @param date calendar instance
     * @return formatted date as String
     */
    default String getFormattedDate(Calendar date) {
        return getFormattedDate(date, MM_dd_yyyy);
    }

    /**
     * Returns current date as formatted string
     *
     * @param format - string to be used as date format
     * @return formatted date as String
     */
    default String getFormattedDate(String format) {
        return getFormattedDate(Calendar.getInstance(), format);
    }

    /**
     * Returns current datetime as string
     *
     * @return formatted datetime as String
     */
    default String getFormattedDateTimeNow() {
        return getFormattedDate(Calendar.getInstance(), dd_MM_yy_H_mm);
    }

    /**
     * Returns date as formatted string
     *
     * @param calendar      calendar instance
     * @param formatPattern pattern string
     * @return formatted date as String
     */
    default String getFormattedDate(Calendar calendar, String formatPattern) {
        return new SimpleDateFormat(formatPattern).format(calendar.getTime());
    }

    /**
     * Returns random generated string
     */
    default String getRandomString() {
        return getRandomString(allCharacters, 16);
    }

    /**
     * Returns timestamp based on the current date and time
     */
    default String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = new GregorianCalendar();
        return sdf.format(calendar.getTime());
    }

    /**
     * Returns random number
     *
     * @param length number length
     */
    default int getRandomNumber(int length) {
        StringBuilder sb = new StringBuilder();
        sb.append(nonZeroDigits.charAt(new Random().nextInt(nonZeroDigits.length())));
        for (int i = 1; i < length; i++) {
            sb.append(withZeroDigits.charAt(new Random().nextInt(withZeroDigits.length())));
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * Returns random generated string
     *
     * @param symbols Initial symbols set
     * @param length  Number of symbols in generated string
     */
    default String getRandomString(String symbols, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(symbols.charAt(new Random().nextInt(symbols.length())));
        }
        return sb.toString();
    }
}

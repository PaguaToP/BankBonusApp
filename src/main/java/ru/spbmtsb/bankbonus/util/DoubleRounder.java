package ru.spbmtsb.bankbonus.util;

/**
 * DoubleRounder - класс для округления double значений
 */
public class DoubleRounder {
    public static double round(double num) {
        return Math.round(num * 100.0) / 100.0;
    }
}

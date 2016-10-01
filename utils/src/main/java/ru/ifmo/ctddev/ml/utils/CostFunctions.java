package ru.ifmo.ctddev.ml.utils;

/**
 * @author Maxim Slyusarenko
 * @since 24.09.16
 */

@SuppressWarnings({"unused", "WeakerAccess"})
public class CostFunctions {

    public static double square(double expected, double actual) {
        return Math.pow(expected - actual, 2);
    }
}

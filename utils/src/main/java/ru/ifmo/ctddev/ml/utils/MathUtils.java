package ru.ifmo.ctddev.ml.utils;

/**
 * @author Alexey Katsman
 * @since 18.09.16
 */

@SuppressWarnings("unused")
public class MathUtils {

    private static final double EPS = 1.0E-9D;

    private MathUtils() {
    }

    public static int compare(double a, double b) {
        return compare(a, b, EPS);
    }

    public static int compare(double a, double b, double eps) {
        return a > b + eps ? 1 : (a < b - eps ? -1 : (Double.isNaN(a) ? 1 : 0) - (Double.isNaN(b) ? 1 : 0));
    }

    public static boolean isLess(double a, double b) {
        return isLess(a, b, EPS);
    }

    public static boolean isLess(double a, double b, double eps) {
        return compare(a, b, eps) == -1;
    }

    public static boolean isLessOrEquals(double a, double b) {
        return isLessOrEquals(a, b, EPS);
    }

    public static boolean isLessOrEquals(double a, double b, double eps) {
        return compare(a, b, eps) < 1;
    }

    public static boolean isGreater(double a, double b) {
        return isGreater(a, b, EPS);
    }

    public static boolean isGreater(double a, double b, double eps) {
        return compare(a, b, eps) == 1;
    }

    public static boolean isGreaterOrEquals(double a, double b) {
        return isGreaterOrEquals(a, b, EPS);
    }

    public static boolean isGreaterOrEquals(double a, double b, double eps) {
        return compare(a, b, eps) > -1;
    }

    public static boolean equals(double a, double b) {
        return equals(a, b, EPS);
    }

    public static boolean equals(double a, double b, double eps) {
        return compare(a, b, eps) == 0;
    }

    public static boolean isZero(double a) {
        return isZero(a, EPS);
    }

    public static boolean isZero(double a, double eps) {
        return compare(a, 0.0d, eps) == 0;
    }

    public static boolean isPositive(double a) {
        return isPositive(a, EPS);
    }

    public static boolean isPositive(double a, double eps) {
        return compare(a, 0.0d, eps) == 1;
    }

    public static boolean isNegative(double a) {
        return isNegative(a, EPS);
    }

    public static boolean isNegative(double a, double eps) {
        return compare(a, 0.0d, eps) == -1;
    }
}

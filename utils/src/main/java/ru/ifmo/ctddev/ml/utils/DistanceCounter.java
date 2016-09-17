package ru.ifmo.ctddev.ml.utils;

import ru.ifmo.ctddev.ml.core.entities.TwoDimensionalPoint;

/**
 * @author Maxim Slyusarenko
 * @since 17.09.16
 */
public class DistanceCounter {

    public static double countMinkowskiDistance(TwoDimensionalPoint first, TwoDimensionalPoint second, int p) {
        return Math.pow(Math.pow(first.getX() - second.getX(), p) + Math.pow(first.getY() - second.getY(), p), 1.0 / (double) p);
    }

    public static double countEuclidDistance(TwoDimensionalPoint first, TwoDimensionalPoint second) {
        return  countMinkowskiDistance(first, second, 2);
    }
}

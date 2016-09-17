package ru.ifmo.ctddev.ml.core.entities;

import lombok.Getter;

/**
 * @author Maxim Slyusarenko
 * @since 17.09.16
 */
@Getter
public class TwoDimensionalPoint {

    private final double x;
    private final double y;

    public TwoDimensionalPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

}

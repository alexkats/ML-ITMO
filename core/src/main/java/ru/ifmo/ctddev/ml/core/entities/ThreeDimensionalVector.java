package ru.ifmo.ctddev.ml.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Maxim Slyusarenko
 * @since 24.09.16
 */
@AllArgsConstructor
@Getter
public class ThreeDimensionalVector {

    private final double x;
    private final double y;
    private final double z;
}

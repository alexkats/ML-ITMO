package ru.ifmo.ctddev.ml.homework2;

import ru.ifmo.ctddev.ml.core.entities.ThreeDimensionalVector;

import java.util.List;

/**
 * @author Maxim Slyusarenko
 * @since 08.10.16
 */
public interface Algorithm {

    ThreeDimensionalVector solve(List<DataSetEntity> entities);
}

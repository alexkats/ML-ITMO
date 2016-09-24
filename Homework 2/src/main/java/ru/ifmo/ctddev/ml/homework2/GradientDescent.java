package ru.ifmo.ctddev.ml.homework2;

import ru.ifmo.ctddev.ml.core.entities.ThreeDimensionalVector;

import java.util.List;
import java.util.function.Function;

/**
 * @author Maxim Slyusarenko
 * @since 24.09.16
 */
public class GradientDescent {

    private final ThreeDimensionalVector INITIAL_VECTOR = new ThreeDimensionalVector(0, 1, 1);
    private final double GRADIENT_STEP = 0.5;
    private final Function<Double, Double> LOSS_FUNCTION = m -> Math.pow(1 - m, 2);
    private final Function<Double, Double> LOSS_FUNCTION_DERIVATIVE = m -> 2 * m - 2;

    public ThreeDimensionalVector solve(List<DataSetEntity> entities) {
        ThreeDimensionalVector current = new ThreeDimensionalVector(0, 1, 1);
        // TODO: implement
        return current;
    }
}

package ru.ifmo.ctddev.ml.homework2;

import ru.ifmo.ctddev.ml.common.MathUtils;
import ru.ifmo.ctddev.ml.core.entities.ThreeDimensionalVector;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author Maxim Slyusarenko
 * @since 24.09.16
 */

public class GradientDescent {

    private final ThreeDimensionalVector INITIAL_VECTOR = new ThreeDimensionalVector(0, 1, 1);
    private final double GRADIENT_STEP = 0.5;
    private final BiFunction<Double, Double, Double> MISTAKE_FUNCTION = (expected, actual) -> Math.pow(expected - actual, 2);
    private final BiFunction<Double, Double, Double> MISTAKE_FUNCTION_DERIVATIVE = (expected, actual) -> 2 * (expected - actual);
    private final double stopIfLess = 1000.0;

    public ThreeDimensionalVector solve(List<DataSetEntity> entities) {
        ThreeDimensionalVector current = new ThreeDimensionalVector(0, 1, 1);
        double lastEmpiricalRisk = -1.0;
        while (MathUtils.equals(lastEmpiricalRisk, -1.0) ||
                MathUtils.isLess(Math.abs(countEmpiricalRisk(entities, current) - lastEmpiricalRisk), stopIfLess)) {
            current = countNextStepVector(current, entities);
        }
        return current;
    }

    private double countEmpiricalRisk(List<DataSetEntity> entities, ThreeDimensionalVector current) {
        double empiricalRisk = 0;
        for (DataSetEntity entity : entities) {
            empiricalRisk += MISTAKE_FUNCTION.apply(countCost(current, entity.getArea(), entity.getRoom()), (double) entity.getPrice());
        }
        return empiricalRisk;
    }

    private double countCost(ThreeDimensionalVector current, int area, int room) {
        return current.getZ() + current.getX() * area + current.getZ() * room;
    }

    private ThreeDimensionalVector countNextStepVector(ThreeDimensionalVector current, List<DataSetEntity> entities) {
        return current.sub(countGradientStep(current, entities));
    }

    private ThreeDimensionalVector countGradientStep(ThreeDimensionalVector current, List<DataSetEntity> entities) {
        ThreeDimensionalVector result = new ThreeDimensionalVector(0, 0, 0);
        for (DataSetEntity entity : entities) {
            ThreeDimensionalVector entityVector = new ThreeDimensionalVector(1, entity.getArea(), entity.getRoom());
            double mistake = MISTAKE_FUNCTION_DERIVATIVE.apply(countCost(current, entity.getArea(), entity.getRoom()),
                    (double) entity.getPrice());
            result = result.add(entityVector.mulOnConstant(mistake));
        }
        return current.sub(result.mulOnConstant(GRADIENT_STEP));
    }
}

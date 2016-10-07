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

    private static final ThreeDimensionalVector INITIAL_VECTOR = new ThreeDimensionalVector(0, 1, 1);
    private static final double GRADIENT_STEP = 0.5;
    private static final BiFunction<Double, Double, Double> MISTAKE_FUNCTION_DERIVATIVE = (expected, actual) -> 2 * (expected - actual);
    private static final double STOP_IF_LESS = 1000.0;
    private static final int MAX_GRADIENT_STEPS = 100000;

    public ThreeDimensionalVector solve(List<DataSetEntity> entities) {
        ThreeDimensionalVector current = INITIAL_VECTOR;
        double lastEmpiricalRisk = -1.0;
        int gradientStepNumber = 0;
        while (gradientStepNumber < MAX_GRADIENT_STEPS && (MathUtils.equals(lastEmpiricalRisk, -1.0) ||
                !MathUtils.isLess(Math.abs(EmpiricalRiskCounter.countEmpiricalRisk(entities, current) - lastEmpiricalRisk), STOP_IF_LESS))) {
            current = countNextStepVector(current, entities);
            gradientStepNumber++;
        }
        return current;
    }

    private ThreeDimensionalVector countNextStepVector(ThreeDimensionalVector current, List<DataSetEntity> entities) {
        return current.sub(countGradientStep(current, entities));
    }

    private ThreeDimensionalVector countGradientStep(ThreeDimensionalVector current, List<DataSetEntity> entities) {
        ThreeDimensionalVector result = new ThreeDimensionalVector(0, 0, 0);
        for (DataSetEntity entity : entities) {
            ThreeDimensionalVector entityVector = new ThreeDimensionalVector(1, entity.getArea(), entity.getRoom());
            double mistake = MISTAKE_FUNCTION_DERIVATIVE.apply(EmpiricalRiskCounter.countCost(current, entity.getArea(), entity.getRoom()),
                    (double) entity.getPrice());
            result = result.add(entityVector.mulOnConstant(mistake));
        }
        return current.sub(result.mulOnConstant(GRADIENT_STEP));
    }
}

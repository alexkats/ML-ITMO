package ru.ifmo.ctddev.ml.homework2;

import ru.ifmo.ctddev.ml.common.MathUtils;
import ru.ifmo.ctddev.ml.core.entities.ThreeDimensionalVector;

import java.util.List;
import java.util.function.Function;

/**
 * @author Maxim Slyusarenko
 * @since 24.09.16
 */

public class GradientDescent implements Algorithm {

    private static final ThreeDimensionalVector INITIAL_VECTOR = new ThreeDimensionalVector(0.1, 0.02, 0.01);
    private static final double GRADIENT_STEP = 0.0016;
    private static final Function<Double, Double> DECREASING_FUNCTION_DERIVATIVE = a -> -Math.exp(-a);
    private static final double STOP_IF_LESS = 0.05;
    private static final int MAX_GRADIENT_STEPS = 1000;

    @Override
    public ThreeDimensionalVector solve(List<DataSetEntity> entities) {
        ThreeDimensionalVector current = INITIAL_VECTOR;
        double lastEmpiricalRisk = -1.0;
        int gradientStepNumber = 0;
        while (gradientStepNumber < MAX_GRADIENT_STEPS && (MathUtils.equals(lastEmpiricalRisk, -1.0) ||
                !MathUtils.isLess(Math.abs(EmpiricalRiskCounter.countEmpiricalRisk(entities, current) - lastEmpiricalRisk), STOP_IF_LESS))) {
            lastEmpiricalRisk = EmpiricalRiskCounter.countEmpiricalRisk(entities, current);
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
            double mistake = DECREASING_FUNCTION_DERIVATIVE.apply(EmpiricalRiskCounter.countCost(current, entity.getArea(),
                    entity.getRoom())) * entity.getPrice();
            result = result.add(entityVector.mulOnConstant(mistake));
        }
        return result.mulOnConstant(GRADIENT_STEP);
    }
}

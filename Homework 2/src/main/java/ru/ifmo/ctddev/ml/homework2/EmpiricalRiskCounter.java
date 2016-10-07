package ru.ifmo.ctddev.ml.homework2;

import ru.ifmo.ctddev.ml.core.entities.ThreeDimensionalVector;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author Maxim Slyusarenko
 * @since 07.10.16
 */
public class EmpiricalRiskCounter {

    private static final BiFunction<Double, Double, Double> MISTAKE_FUNCTION = (expected, actual) -> Math.pow(expected - actual, 2);

    public  static double countEmpiricalRisk(List<DataSetEntity> entities, ThreeDimensionalVector current) {
        double empiricalRisk = 0;
        for (DataSetEntity entity : entities) {
            empiricalRisk += MISTAKE_FUNCTION.apply(countCost(current, entity.getArea(), entity.getRoom()), (double) entity.getPrice());
        }
        return empiricalRisk;
    }

    public static double countCost(ThreeDimensionalVector current, int area, int room) {
        return current.getZ() + current.getX() * area + current.getZ() * room;
    }
}

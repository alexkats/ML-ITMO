package ru.ifmo.ctddev.ml.utils;

import ru.ifmo.ctddev.ml.core.entities.DataSetDistance;

import java.util.List;
import java.util.function.Function;

/**
 * @author Maxim Slyusarenko
 * @since 18.09.16
 */
public class SpatialTransformations {

    private static final int POWER = 5;
    private static final Function<Double, Double> KERNEL_FUNCTION = aDouble -> 3 * aDouble * aDouble / 2;
    private static final double H = 0.5;

    public static double simpleTransformation(List<DataSetDistance> distances, Integer indexInList,
                                              Integer nearestNeighborsCount) {
        return 1.0;
    }

    public static double powerTransformation(List<DataSetDistance> distances, Integer indexInList,
                                             Integer nearestNeighborsCount) {
        return Math.pow(distances.get(indexInList).getDistance(), POWER);
    }

    public static double fixedParzenRosenblattWindow(List<DataSetDistance> distances, Integer indexInList,
                                                     Integer nearestNeighborsCount) {
        double functionArg = distances.get(indexInList).getDistance() / H;
        if (Math.abs(functionArg) > 1.0) {
            return 0;
        } else {
            return KERNEL_FUNCTION.apply(functionArg);
        }
    }

    public static double variableParzenRosenblattWindow(List<DataSetDistance> distances, Integer indexInList,
                                                        Integer nearestNeighborsCount) {

        double functionArg = distances.get(indexInList).getDistance() / distances.get(nearestNeighborsCount + 1).getDistance();
        if (Math.abs(functionArg) > 1.0) {
            return 0;
        } else {
            return KERNEL_FUNCTION.apply(functionArg);
        }
    }
}

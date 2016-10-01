package ru.ifmo.ctddev.ml.homework1;

import ru.ifmo.ctddev.ml.common.MathUtils;
import ru.ifmo.ctddev.ml.core.entities.DataSetDistance;
import ru.ifmo.ctddev.ml.core.entities.TwoDimensionalPoint;
import ru.ifmo.ctddev.ml.core.interfaces.TriFunction;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author Maxim Slyusarenko
 * @since 17.09.16
 */

@SuppressWarnings("WeakerAccess")
public class KNNAlgorithm {

    private static final int CLASSES_NUMBER = 2;
    private static final int NEIGHBORS_NUMBER = 3;

    private final BiFunction<TwoDimensionalPoint, TwoDimensionalPoint, Double> distanceCounter;
    private final TriFunction<List<DataSetDistance>, Integer, Integer, Double> weightCalculator; // from List of distances, current distance in list number, chosen K to weight of current point
    private final List<DataSetEntity> trainingDataSet;

    public KNNAlgorithm(BiFunction<TwoDimensionalPoint, TwoDimensionalPoint, Double> distanceCounter,
                        TriFunction<List<DataSetDistance>, Integer, Integer, Double> weightCalculator,
                        List<DataSetEntity> trainingDataSet)
    {
        this.distanceCounter = distanceCounter;
        this.weightCalculator = weightCalculator;
        this.trainingDataSet = trainingDataSet;
    }

    public List<DataSetEntity> solve(List<DataSetEntity> askingDataSet) {
        return askingDataSet.stream().map(entity -> new DataSetEntity(entity.getFeature(), getChosenPointClass(entity)))
                .collect(Collectors.toList());
    }

    private int getChosenPointClass(DataSetEntity point) {
        List<DataSetDistance> distances = trainingDataSet.stream()
                .map(trainingSet -> new DataSetDistance(distanceCounter.apply(trainingSet.getFeature(), point.getFeature()), trainingSet.getEntityClass()))
                .collect(Collectors.toList());
        distances.sort((o1, o2) -> MathUtils.compare(o1.getDistance(), o2.getDistance()));
        double[] classNumberMentions = initiateClassNumberMentions();
        for (int i = 0; i < trainingDataSet.size(); i++) {
            classNumberMentions[distances.get(i).getEntityClass()] += weightCalculator.apply(distances, i,
                    NEIGHBORS_NUMBER);
        }
        return getChosenClass(classNumberMentions);
    }

    private double[] initiateClassNumberMentions() {
        double[] result = new double[CLASSES_NUMBER];
        for (int i = 0; i < CLASSES_NUMBER; i++) {
            result[i] = 0.0;
        }
        return result;
    }

    private int getChosenClass(double[] classNumbers) {
        double maxNumber = -1;
        int classNumber = -1;
        for (int i = 0; i < classNumbers.length; i++) {
            if (MathUtils.isGreater(classNumbers[i], maxNumber)) {
                classNumber = i;
                maxNumber = classNumbers[i];
            }
        }
        return classNumber;
    }
}

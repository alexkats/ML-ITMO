package ru.ifmo.ctddev.ml.homework1;

import ru.ifmo.ctddev.ml.core.entities.DataSetDistance;
import ru.ifmo.ctddev.ml.core.entities.TwoDimensionalPoint;
import ru.ifmo.ctddev.ml.core.interfaces.TriFunction;
import ru.ifmo.ctddev.ml.homework1.splitter.DataSetSplitter;
import ru.ifmo.ctddev.ml.homework1.splitter.DefaultDataSetSplitter;
import ru.ifmo.ctddev.ml.utils.MathUtils;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author Maxim Slyusarenko
 * @since 19.09.16
 */
public class KFoldValidation {

    public static final int FOLD_NUMBER = 5;

    private final DataSetSplitter<DataSetEntity> splitter;
    private final BiFunction<TwoDimensionalPoint, TwoDimensionalPoint, Double> distanceCounter;
    private final TriFunction<List<DataSetDistance>, Integer, Integer, Double> weightCalculator;

    public KFoldValidation(List<DataSetEntity> dataSet,
                           BiFunction<TwoDimensionalPoint, TwoDimensionalPoint, Double> distanceCounter,
                           TriFunction<List<DataSetDistance>, Integer, Integer, Double> weightCalculator) {
        this.splitter = new DefaultDataSetSplitter<>(dataSet, FOLD_NUMBER, true);
        this.distanceCounter = distanceCounter;
        this.weightCalculator = weightCalculator;
    }

    public KNNAlgorithm getBestTrainedAlgorithm() {
        double maxQuality = -1.0;
        KNNAlgorithm perfectAlgorithm = null;
        splitter.setPartsQuantity(FOLD_NUMBER);

        for (int i = 0; i < FOLD_NUMBER; i++) {
            splitter.split(i);
            List<DataSetEntity> trainingDataSet = splitter.getTrainingDataSet();
            List<DataSetEntity> testingDataSet = splitter.getTestingDataSet();
            KNNAlgorithm algorithm = new KNNAlgorithm(distanceCounter, weightCalculator, trainingDataSet);
            List<DataSetEntity> algorithmResult = algorithm.solve(testingDataSet);
            double quality = getQuality(testingDataSet, algorithmResult);

            if (MathUtils.isGreater(quality, maxQuality)) {
                maxQuality = quality;
                perfectAlgorithm = algorithm;
            }
        }

        return perfectAlgorithm;
    }

    private double getQuality(List<DataSetEntity> expected, List<DataSetEntity> actual) {
        int mistakes = 0;

        for (int i = 0; i < expected.size(); i++) {
            if (expected.get(i).getEntityClass() != actual.get(i).getEntityClass()) {
                mistakes++;
            }
        }

        return 1.0 - (double) mistakes / (double) expected.size();
    }
}

package ru.ifmo.ctddev.ml.homework1;

import ru.ifmo.ctddev.ml.core.entities.DataSetDistance;
import ru.ifmo.ctddev.ml.core.entities.TwoDimensionalPoint;
import ru.ifmo.ctddev.ml.core.interfaces.FourFunction;
import ru.ifmo.ctddev.ml.homework1.splitter.DataSetSplitter;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author Maxim Slyusarenko
 * @since 17.09.16
 */
public class KNNAlgorithm {

    private static final int FOLD_NUMBER = 10;
    private static final int CLASSES_NUMBER = 2;

    private final DataSetSplitter<DataSetEntity> splitter;
    private final BiFunction<TwoDimensionalPoint, TwoDimensionalPoint, Double> distanceCounter;
    private final FourFunction<TwoDimensionalPoint, List<DataSetDistance>, Integer, Integer, Double> spatialTransformation; // from point, List of distances, current distance in list number, chosen K to weight of current point

    public KNNAlgorithm(DataSetSplitter<DataSetEntity> splitter,
                        BiFunction<TwoDimensionalPoint, TwoDimensionalPoint, Double> distanceCounter,
                        FourFunction<TwoDimensionalPoint, List<DataSetDistance>, Integer, Integer, Double> spatialTransformation) {
        this.splitter = splitter;
        this.distanceCounter = distanceCounter;
        this.spatialTransformation = spatialTransformation;
    }

    public int getOptimalK() {
        int optimalK = -1;
        double minAverageMistakes = -1.0;
        for (int i = 0; i < FOLD_NUMBER; i++) {
            int sumMistakes = 0;
            for (int j = 0; j < i; j++) {
                splitter.split(i);
                sumMistakes += countMistakes(splitter.getTrainingDataSet(j), splitter.getTestingDataSet(j));
            }
            double averageMistakes = (double) sumMistakes / (double) i;
            if (optimalK == -1 || averageMistakes < minAverageMistakes) {
                optimalK = i;
                minAverageMistakes = averageMistakes;
            }
        }
        return optimalK;
    }

    public int getChosenPointClass(DataSetEntity point, List<DataSetEntity> trainingDataSet) {
        List<DataSetDistance> distances = trainingDataSet.stream()
                .map(trainingSet -> new DataSetDistance(distanceCounter.apply(trainingSet.getFeature(), point.getFeature()), trainingSet.getEntityClass()))
                .collect(Collectors.toList());
        distances.sort((o1, o2) -> o1.getDistance().compareTo(o2.getDistance()));
        double[] classNumberMentions = initiateClassNumberMentions();
        for (int i = 0; i < trainingDataSet.size(); i++) {
            classNumberMentions[distances.get(i).getEntityClass()] += spatialTransformation.apply(point.getFeature(),
                    distances, i, trainingDataSet.size());
        }
        return getChosenClass(classNumberMentions);
    }

    private int countMistakes(List<DataSetEntity> trainingDataSet, List<DataSetEntity> testingDataSet) {
        int mistakes = 0;
        for (DataSetEntity test : testingDataSet) {
            int chosenClass = getChosenPointClass(test, trainingDataSet);
            if (chosenClass != test.getEntityClass()) {
                mistakes++;
            }
        }
        return mistakes;
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
            if (classNumbers[i] > maxNumber) {
                classNumber = i;
                maxNumber = classNumbers[i];
            }
        }
        return classNumber;
    }
}

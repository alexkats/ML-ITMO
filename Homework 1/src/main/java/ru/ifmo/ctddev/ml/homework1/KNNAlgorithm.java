package ru.ifmo.ctddev.ml.homework1;

import ru.ifmo.ctddev.ml.core.entities.TwoDimensionalPoint;
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
    private DataSetSplitter<DataSetEntity> splitter;

    private final BiFunction<TwoDimensionalPoint, TwoDimensionalPoint, Double> distanceCounter;

    public KNNAlgorithm(BiFunction<TwoDimensionalPoint, TwoDimensionalPoint, Double> distanceCounter) {
        this.distanceCounter = distanceCounter;
    }

    public int getOptimalK() {
        int optimalK = -1;
        double minAverageMistakes = -1.0;
        for (int i = 0; i < FOLD_NUMBER; i++) {
            int sumMistakes = 0;
            for (int j = 0; j < i; j++) {
                splitter.split(i);
                sumMistakes += countMistakes(splitter.getTrainingDataSet(j), splitter.getTestingDataSet(j), i);
            }
            double averageMistakes = (double) sumMistakes / (double) i;
            if (optimalK == -1 || averageMistakes < minAverageMistakes) {
                optimalK = i;
                minAverageMistakes = averageMistakes;
            }
        }
        return optimalK;
    }

    private int countMistakes(List<DataSetEntity> trainingDataSet, List<DataSetEntity> testingDataSet, int nearestPointsNumber) {
        int mistakes = 0;
        for (DataSetEntity test : testingDataSet) {
            List<DataSetDistance> distances = trainingDataSet.stream()
                    .map(trainingSet -> new DataSetDistance(distanceCounter.apply(trainingSet.getFeature(), test.getFeature()), trainingSet.getEntityClass()))
                    .collect(Collectors.toList());
            distances.sort((o1, o2) -> o1.getDistance().compareTo(o2.getDistance()));
            int[] classNumberMentions = initiateClassNumberMentions();
            for (int i = 0; i < nearestPointsNumber; i++) {
                classNumberMentions[distances.get(i).getEntityClass()]++;
            }
            int chosenClass = getChosenClass(classNumberMentions);
            if (chosenClass != test.getEntityClass()) {
                mistakes++;
            }
        }
        return mistakes;
    }

    private int[] initiateClassNumberMentions() {
        int[] result = new int[CLASSES_NUMBER];
        for (int i = 0; i < CLASSES_NUMBER; i++) {
            result[i] = 0;
        }
        return result;
    }

    private int getChosenClass(int[] classNumbers) {
        int maxNumber = -1;
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

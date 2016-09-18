package ru.ifmo.ctddev.ml.homework1;

import ru.ifmo.ctddev.ml.core.entities.DataSetDistance;
import ru.ifmo.ctddev.ml.core.entities.TwoDimensionalPoint;
import ru.ifmo.ctddev.ml.core.interfaces.TriFunction;
import ru.ifmo.ctddev.ml.homework1.splitter.DataSetSplitter;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author Maxim Slyusarenko
 * @since 18.09.16
 */
public class KNNCaller {

    private final DataSetSplitter<DataSetEntity> splitter;
    private final KNNAlgorithm algorithm;

    public KNNCaller(DataSetSplitter<DataSetEntity> splitter, BiFunction<TwoDimensionalPoint, TwoDimensionalPoint, Double> distanceCounter,
                     TriFunction<List<DataSetDistance>, Integer, Integer, Double> spatialTransformation) {
        this.splitter = splitter;
        this.algorithm = new KNNAlgorithm(splitter, distanceCounter, spatialTransformation);
    }

    public void splitToOptimalTrainingTestingDataSets() {
        splitter.setPartsQuantity(algorithm.getOptimalK());
        splitter.split(0);
    }

    public List<DataSetEntity> getOptimalTrainingDataSet() {
        return splitter.getTrainingDataSet();
    }

    public List<DataSetEntity> getOptimalTestingDataSet() {
        return splitter.getTestingDataSet();
    }

    public List<DataSetEntity> getDataClassificationByAlgorithm(List<DataSetEntity> trainingDataSet, List<DataSetEntity> askingDataSet) {
        return askingDataSet.stream()
                .map(askPoint -> new DataSetEntity(askPoint.getFeature(), algorithm.getChosenPointClass(askPoint, trainingDataSet)))
                .collect(Collectors.toList());
    }
}

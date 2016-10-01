package ru.ifmo.ctddev.ml.homework1.splitter;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Katsman
 * @since 17.09.16
 */

@Getter
public class DefaultDataSetSplitter<E> implements DataSetSplitter<E> {

    private final List<E> dataSet;
    private int partSize;
    private int partsQuantity;
    private List<E> trainingDataSet;
    private List<E> testingDataSet;

    private static final int DEFAULT_PARTS_QUANTITY = 10;

    @SuppressWarnings("unused")
    public DefaultDataSetSplitter(List<E> dataSet) {
        this(dataSet, DEFAULT_PARTS_QUANTITY, false);
    }

    @SuppressWarnings("unused")
    public DefaultDataSetSplitter(List<E> dataSet, boolean createInstance) {
        this(dataSet, DEFAULT_PARTS_QUANTITY, createInstance);
    }

    @SuppressWarnings("unused")
    public DefaultDataSetSplitter(List<E> dataSet, int partsQuantity) {
        this(dataSet, partsQuantity, false);
    }

    @SuppressWarnings("unused")
    public DefaultDataSetSplitter(List<E> dataSet, int partsQuantity, boolean createInstance) {
        this.dataSet = createInstance ? new ArrayList<>(dataSet) : dataSet;
        this.partsQuantity = partsQuantity;
        partSize = dataSet.size() / partsQuantity;
    }

    @Override
    public void setPartsQuantity(int partsQuantity) {
        this.partsQuantity = partsQuantity + 1;
        partSize = dataSet.size() / this.partsQuantity;
    }

    @Override
    public void split(int partNumber) {
        trainingDataSet = new ArrayList<>(dataSet.subList(0, partNumber * partsQuantity));
        trainingDataSet.addAll(dataSet.subList((partNumber + 1) * partsQuantity, dataSet.size()));
        testingDataSet = new ArrayList<>(dataSet.subList(partNumber * partsQuantity, (partNumber + 1) * partsQuantity));
    }

    @Override
    public List<E> splitAndGetTrainingDataSet(int partNumber) {
        split(partNumber);
        return getTrainingDataSet();
    }

    @Override
    public List<E> splitAndGetTestingDataSet(int partNumber) {
        split(partNumber);
        return getTestingDataSet();
    }
}

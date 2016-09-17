package ru.ifmo.ctddev.ml.homework1.splitter;

import lombok.Getter;

import java.util.List;

/**
 * @author Alexey Katsman
 * @since 17.09.16
 */

public class DefaultDataSetSplitter<E> implements DataSetSplitter<E> {

    private List<E> dataSet;

    @Getter
    private int partSize;
    @Getter
    private int partsQuantity;
    private List<E> trainingDataSet;
    private List<E> testingDataSet;

    public DefaultDataSetSplitter(List<E> dataSet, int partsQuantity) {
        this.dataSet = dataSet;
    }

    @Override
    public void split(int k) {

    }

    @Override
    public List<E> getTrainingDataSet(int partNumber) {
        return null;
    }

    @Override
    public List<E> getTestingDataSet(int partNumber) {
        return null;
    }
}

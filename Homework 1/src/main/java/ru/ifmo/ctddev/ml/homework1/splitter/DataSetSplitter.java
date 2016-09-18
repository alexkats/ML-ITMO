package ru.ifmo.ctddev.ml.homework1.splitter;

import java.util.List;

/**
 * @author Alexey Katsman
 * @since 17.09.16
 */

public interface DataSetSplitter<E> {

    void split(int k);

    int getPartSize();

    int getPartsQuantity();

    List<E> getTrainingDataSet(int partNumber);

    List<E> getTestingDataSet(int partNumber);

    List<E> getAllDataSet();
}

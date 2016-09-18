package ru.ifmo.ctddev.ml.homework1.splitter;

import java.util.List;

/**
 * @author Alexey Katsman
 * @since 17.09.16
 */

public interface DataSetSplitter<E> {

    /**
     * Splits data set into <tt>2</tt> parts:
     * <tt>training data set</tt> and <tt>testing data set</tt>
     *
     * @param partNumber number of part of splitted data set to be added to testing data set
     */
    void split(int partNumber);

    /**
     * Returns size of each part of data set
     * @return size of each part of data set
     */
    int getPartSize();


    /**
     * Sets quantity of parts for splitting data set (aka <tt>k</tt>)
     *
     * @param partsQuantity quantity of parts for splitting data set
     */
    void setPartsQuantity(int partsQuantity);

    /**
     * Returns quantity of parts for splitting data set (aka <tt>k</tt>)
     *
     * @return quantity of parts for splitting data set
     */
    int getPartsQuantity();

    /**
     * Returns training data set after splitting data set
     *
     * @return training data set after splitting data set
     * @throws NullPointerException if {@link #split(int)} was not called before
     */
    List<E> getTrainingDataSet();

    /**
     * Returns testing data set after splitting data set
     *
     * @return testing data set after splitting data set
     * @throws NullPointerException if {@link #split(int)} was not called before
     */
    List<E> getTestingDataSet();

    /**
     * Splits data set into <tt>2</tt> parts
     * and returns training data set
     *
     * @param partNumber number of part of splitted data set to be added to testing data set
     * @return training data set after splitting data set
     */
    List<E> splitAndGetTrainingDataSet(int partNumber);

    /**
     * Splits data set into <tt>2</tt> parts
     * and returns testing data set
     *
     * @param partNumber number of part of splitted data set to be added to testing data set
     * @return testing data set after splitting data set
     */
    List<E> splitAndGetTestingDataSet(int partNumber);

    /**
     * Returns original data set
     *
     * @return original data set
     */
    List<E> getDataSet();
}

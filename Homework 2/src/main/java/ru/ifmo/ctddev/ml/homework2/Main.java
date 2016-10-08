package ru.ifmo.ctddev.ml.homework2;

import ru.ifmo.ctddev.ml.core.entities.TwoDimensionalPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Katsman
 * @since 24.09.16
 */

public class Main {

    private static List<DataSetEntity> dataSet;
    private static final String FILE_NAME = "Homework 2/prices.txt";
    private static final int GRADIENT_DESCENT_ALGORITHM_NUMBER = 0;
    private static final int GENETIC_ALGORITHM_NUMBER = 1;

    public static void main(String[] args) throws IOException {
        constructDataSet();
        AlgorithmRunner runner = new AlgorithmRunner(dataSet, GRADIENT_DESCENT_ALGORITHM_NUMBER);
        System.out.println(runner.getBestVector());
    }

    private static void constructDataSet() throws IOException {
        dataSet = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));

        while (true) {
            String line = bufferedReader.readLine();

            if (line == null) {
                break;
            }

            String[] splittedLine = line.split(",");
            DataSetEntity entity = new DataSetEntity(Integer.parseInt(splittedLine[0]),
                    Integer.parseInt(splittedLine[1]), Integer.parseInt(splittedLine[2]));
            dataSet.add(entity);
        }
    }
}

package ru.ifmo.ctddev.ml.homework1;

import ru.ifmo.ctddev.ml.core.entities.TwoDimensionalPoint;
import ru.ifmo.ctddev.ml.core.ui.UIException;
import ru.ifmo.ctddev.ml.homework1.ui.UIStarter;
import ru.ifmo.ctddev.ml.utils.DistanceCounter;
import ru.ifmo.ctddev.ml.utils.WeightCalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author Alexey Katsman
 * @since 17.09.16
 */

public class Main {

    private static List<DataSetEntity> dataSet = new ArrayList<>();
    private static final String FILE_NAME = "Homework 1/chips.txt";

    public static void main(String[] args) throws IOException {
        constructDataSet();
        Collections.shuffle(dataSet);
        KFoldValidation validation = new KFoldValidation(dataSet, DistanceCounter::countEuclidDistance, WeightCalculator::variableParzenRosenblattWindow);

        KNNAlgorithm algorithm = validation.getBestTrainedAlgorithm();
        List<DataSetEntity> algorithmResult = algorithm.solve(dataSet);
        try {
            UIStarter.start(dataSet, algorithmResult);
            System.out.printf(Locale.ENGLISH, "Accuracy: %.2f%%\n", validation.getQuality(dataSet, algorithmResult) * 100.0d);
        } catch (UIException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    private static void constructDataSet() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));

        while (true) {
            String line = bufferedReader.readLine();

            if (line == null) {
                break;
            }

            String[] splittedLine = line.split(",");
            DataSetEntity entity = new DataSetEntity(new TwoDimensionalPoint(Double.parseDouble(splittedLine[0]),
                    Double.parseDouble(splittedLine[1])),
                    Integer.parseInt(splittedLine[2]));
            dataSet.add(entity);
        }
    }
}

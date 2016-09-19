package ru.ifmo.ctddev.ml.homework1;

import ru.ifmo.ctddev.ml.core.entities.TwoDimensionalPoint;
import ru.ifmo.ctddev.ml.homework1.ui.UIException;
import ru.ifmo.ctddev.ml.homework1.ui.UIStarter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Katsman
 * @since 17.09.16
 */

public class Main {

    private static List<DataSetEntity> dataSet = new ArrayList<>();
    private static final String FILE_NAME = "Homework 1/chips.txt";

    public static void main(String[] args) throws IOException {
        constructDataSet();

        try {
            UIStarter.start(dataSet);
        } catch (UIException e) {
            e.printStackTrace();
        }
    }

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

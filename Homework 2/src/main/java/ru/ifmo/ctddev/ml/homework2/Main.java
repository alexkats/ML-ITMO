package ru.ifmo.ctddev.ml.homework2;

import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;
import ru.ifmo.ctddev.ml.core.entities.ThreeDimensionalVector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Alexey Katsman
 * @since 24.09.16
 */

public class Main {

    private static List<DataSetEntity> dataSet;
    private static DataSetNormalizator normalizator;
    private static final String FILE_NAME = "Homework 2/prices.txt";
    private static final int GRADIENT_DESCENT_ALGORITHM_NUMBER = 0;
    private static final int GENETIC_ALGORITHM_NUMBER = 1;

    private static Coord3d[] points;
    private static Shape surface;

    public static void main(String[] args) throws IOException {
        constructDataSet();
        normalizator = new DataSetNormalizator(dataSet);
        AlgorithmRunner runner = new AlgorithmRunner(normalizator.normalize(), GRADIENT_DESCENT_ALGORITHM_NUMBER);
//        constructSurface(runner.getBestVector());
//        constructPoints();
//        ScatterMultiColor scatter = new ScatterMultiColor(points, new ColorMapper(new ColorMapRainbow(), -1.0f, 1.0f));
//        scatter.setWidth(5.0f);
//        Chart chart = AWTChartComponentFactory.chart(Quality.Advanced, "newt");
//        chart.getScene().add(scatter);
//        chart.getScene().getGraph().add(surface);
//        ChartLauncher.openChart(chart);
//        System.out.println(runner.getBestVector());
        double error = 0.0;
        for (DataSetEntity entity : dataSet) {
            error += Math.abs(entity.getPrice() - runner.getPriceForFlat((int) entity.getArea(), (int) entity.getRoom(), normalizator.getNormalizationArea(), normalizator.getNormalizationRoom(), normalizator.getNormalizationPrice()));
            System.out.println(Math.abs(entity.getPrice() - runner.getPriceForFlat((int) entity.getArea(), (int) entity.getRoom(), normalizator.getNormalizationArea(), normalizator.getNormalizationRoom(), normalizator.getNormalizationPrice())));
        }
        System.out.println(runner.getBestVector());
        System.out.printf(Locale.ENGLISH, "%.2f", error / dataSet.size());
//        System.out.println(runner.getPriceForFlat(2526, 3, normalizator.getNormalizationArea(), normalizator.getNormalizationRoom(), normalizator.getNormalizationPrice()));
    }

    private static void constructSurface(ThreeDimensionalVector vector) {
        Mapper mapper = new Mapper() {
            @Override
            public double f(double v, double v1) {
                return vector.getX() * v + vector.getY() * v1 + vector.getZ();
            }
        };

        Range range = new Range(-1.0, 1.0);
        surface = Builder.buildOrthonormal(new OrthonormalGrid(range, 50, range, 50), mapper);
        surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(false);
        surface.setWireframeColor(Color.BLACK);
    }

    private static void constructPoints() {
        points = new Coord3d[dataSet.size()];
        int i = 0;

        for (DataSetEntity dataSetEntity : normalizator.normalize()) {
            points[i++] = new Coord3d(dataSetEntity.getArea(), dataSetEntity.getRoom(), dataSetEntity.getPrice());
        }
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

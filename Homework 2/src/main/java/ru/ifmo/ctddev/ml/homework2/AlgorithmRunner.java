package ru.ifmo.ctddev.ml.homework2;

import ru.ifmo.ctddev.ml.core.entities.ThreeDimensionalVector;

import java.util.List;

/**
 * @author Maxim Slyusarenko
 * @since 08.10.16
 */
public class AlgorithmRunner {

    private final List<DataSetEntity> entities;
    private final Algorithm algorithm;

    public AlgorithmRunner(List<DataSetEntity> entities, int algorithmNumber) {
        this.entities = entities;
        if (algorithmNumber == 0) {
            this.algorithm = new GradientDescent();
        } else {
            this.algorithm = new GeneticAlgorithm();
        }
    }

    public ThreeDimensionalVector getBestVector() {
        return algorithm.solve(entities);
    }

    public int getPriceForFlat(int area, int rooms) {
        ThreeDimensionalVector bestVector = getBestVector();
        return (int) (bestVector.getZ() + bestVector.getX() * area + bestVector.getY() * rooms);
    }
}

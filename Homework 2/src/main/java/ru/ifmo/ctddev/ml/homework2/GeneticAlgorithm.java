package ru.ifmo.ctddev.ml.homework2;

import ru.ifmo.ctddev.ml.common.MathUtils;
import ru.ifmo.ctddev.ml.core.entities.ThreeDimensionalVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Maxim Slyusarenko
 * @since 07.10.16
 */
public class GeneticAlgorithm implements Algorithm {

    private static final int POPULATION_SIZE = 50;
    private static final int FIXED_POPULATION_SIZE = 4;
    private static final int MAX_GENERATION_COUNT = 1000;

    @Override
    public ThreeDimensionalVector solve(List<DataSetEntity> entities) {
        List<ThreeDimensionalVector> population = formPopulation();
        sortPopulation(entities, population);
        double bestEmpiricalRisk = EmpiricalRiskCounter.countEmpiricalRisk(entities, population.get(0));
        double bestEmpiricalRiskEver = bestEmpiricalRisk;
        ThreeDimensionalVector bestVector = population.get(0);
        int generationNumber = 0;
        while (generationNumber < MAX_GENERATION_COUNT) {
            population = formNewGeneration(population);
            sortPopulation(entities, population);
            bestEmpiricalRisk = EmpiricalRiskCounter.countEmpiricalRisk(entities, population.get(0));
            if (MathUtils.isLess(bestEmpiricalRisk, bestEmpiricalRiskEver)) {
                bestEmpiricalRiskEver = bestEmpiricalRisk;
                bestVector = population.get(0);
            }
            generationNumber++;
        }
        return bestVector;
    }

    private void sortPopulation(List<DataSetEntity> entities, List<ThreeDimensionalVector> population) {
        population.sort((o1, o2) -> {
            double firstEmpiricalRisk = EmpiricalRiskCounter.countEmpiricalRisk(entities, o1);
            double secondEmpiricalRisk = EmpiricalRiskCounter.countEmpiricalRisk(entities, o2);
            return Double.valueOf(secondEmpiricalRisk).compareTo(firstEmpiricalRisk);
        });
    }

    private List<ThreeDimensionalVector> formPopulation() {
        Random random = new Random();
        List<ThreeDimensionalVector> population = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(new ThreeDimensionalVector(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        }
        return population;
    }

    private ThreeDimensionalVector crossing(ThreeDimensionalVector first, ThreeDimensionalVector second) {
        return new ThreeDimensionalVector((first.getX() + second.getX()) / 2, (first.getY() + second.getY()) / 2,
                (first.getZ() + second.getZ()) / 2);
    }

    private ThreeDimensionalVector mutation(ThreeDimensionalVector mutate) {
        Random random = new Random();
        int index = random.nextInt(4);
        if (index == 0) {
            return new ThreeDimensionalVector(random.nextDouble(), mutate.getY(), mutate.getZ());
        } else if (index == 1) {
            return new ThreeDimensionalVector(mutate.getX(), random.nextDouble(), mutate.getZ());
        } else if (index == 2) {
            return new ThreeDimensionalVector(mutate.getX(), mutate.getY(), random.nextDouble());
        } else {
            return mutate;
        }
    }

    /**
     *
     * @param population Sorted list of ThreeDimensionalVector
     * @return New generation
     */
    private List<ThreeDimensionalVector> formNewGeneration(List<ThreeDimensionalVector> population) {
        List<ThreeDimensionalVector> toFormNewGeneration = new ArrayList<>();
        for (int i = 0; i < FIXED_POPULATION_SIZE; i++) {
            toFormNewGeneration.add(population.get(i));
        }
        for (int i = 0; i < POPULATION_SIZE - 1; i++) {
            toFormNewGeneration.add(crossing(population.get(i), population.get(i + 1)));
        }
        for (int i = FIXED_POPULATION_SIZE + 1; i < toFormNewGeneration.size(); i++) {
            toFormNewGeneration.set(i, mutation(toFormNewGeneration.get(i)));
        }
        return new ArrayList<>(toFormNewGeneration.subList(0, POPULATION_SIZE));
    }
}

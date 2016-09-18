package ru.ifmo.ctddev.ml.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Maxim Slyusarenko
 * @since 18.09.16
 */
@AllArgsConstructor
@Getter
public class DataSetDistance {

    private Double distance;
    private int entityClass;
}

package ru.ifmo.ctddev.ml.homework2;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Maxim Slyusarenko
 * @since 24.09.16
 */
@AllArgsConstructor
@Getter
public class DataSetEntity {

    private final int area;
    private final int room;
    private final int price;
}

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

    private final double area;
    private final double room;
    private final double price;

    @Override
    public int hashCode() {
        int hash = (int) area;
        hash += 31 * room;
        hash += 31 * 31 * price;

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        DataSetEntity other = (DataSetEntity) obj;

        return area == other.area && room == other.room && price == other.price;
    }
}

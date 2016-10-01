package ru.ifmo.ctddev.ml.homework1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.ifmo.ctddev.ml.core.entities.TwoDimensionalPoint;

/**
 * @author Alexey Katsman
 * @since 17.09.16
 */

@AllArgsConstructor
@Getter
public class DataSetEntity {

    private TwoDimensionalPoint feature;
    private int entityClass;

    @Override
    public int hashCode() {
        int hash = feature.hashCode();
        hash += 29 * entityClass;

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

        return feature.equals(other.feature) && entityClass == other.entityClass;
    }
}

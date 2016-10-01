package ru.ifmo.ctddev.ml.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.ifmo.ctddev.ml.common.MathUtils;

/**
 * @author Maxim Slyusarenko
 * @since 18.09.16
 */

@AllArgsConstructor
@Getter
public class DataSetDistance {

    private Double distance;
    private int entityClass;

    @Override
    public int hashCode() {
        int hash = distance.hashCode();
        hash += 31 * entityClass;

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

        DataSetDistance other = (DataSetDistance) obj;

        return distance.equals(other.distance)
                && MathUtils.equals(distance, other.distance)
                && entityClass == other.entityClass;
    }
}

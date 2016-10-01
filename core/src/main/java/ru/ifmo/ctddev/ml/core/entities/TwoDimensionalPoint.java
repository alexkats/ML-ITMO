package ru.ifmo.ctddev.ml.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.ifmo.ctddev.ml.common.MathUtils;

/**
 * @author Maxim Slyusarenko
 * @since 17.09.16
 */

@AllArgsConstructor
@Getter
public class TwoDimensionalPoint {

    private final double x;
    private final double y;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        TwoDimensionalPoint other = (TwoDimensionalPoint) obj;

        boolean equals = MathUtils.equals(x, other.x);
        equals &= MathUtils.equals(y, other.y);

        return equals;
    }
}

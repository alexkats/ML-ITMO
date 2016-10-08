package ru.ifmo.ctddev.ml.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.ifmo.ctddev.ml.common.MathUtils;

/**
 * @author Maxim Slyusarenko
 * @since 24.09.16
 */

@AllArgsConstructor
@Getter
public class ThreeDimensionalVector {

    private final double x;
    private final double y;
    private final double z;

    @Override
    public int hashCode() {
        int hash = Double.hashCode(x);
        hash += 31 * Double.hashCode(y);
        hash += 31 * 31 * Double.hashCode(z);

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

        ThreeDimensionalVector other = (ThreeDimensionalVector) obj;

        boolean equals = MathUtils.equals(x, other.x);
        equals &= MathUtils.equals(y, other.y);
        equals &= MathUtils.equals(z, other.z);

        return equals;
    }

    @Override
    public String toString() {
        return x + " * x + " + y + " * y + " + z;
    }

    public ThreeDimensionalVector sub(ThreeDimensionalVector other) {
        return new ThreeDimensionalVector(x - other.getX(), y - other.getZ(), z - other.getZ());
    }

    public ThreeDimensionalVector add(ThreeDimensionalVector other) {
        return new ThreeDimensionalVector(x + other.getX(), y + other.getZ(), z + other.getZ());
    }

    public ThreeDimensionalVector mulOnConstant(double constant) {
        return new ThreeDimensionalVector(x * constant, y * constant, z * constant);
    }
}

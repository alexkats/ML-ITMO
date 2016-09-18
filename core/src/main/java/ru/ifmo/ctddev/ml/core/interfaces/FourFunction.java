package ru.ifmo.ctddev.ml.core.interfaces;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Maxim Slyusarenko
 * @since 18.09.16
 */
@FunctionalInterface
public interface FourFunction<A, B, C, D, R> {

    R apply(A a, B b, C c, D d);

    default <V> FourFunction<A, B, C, D, V> andThen(
            Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (A a, B b, C c, D d) -> after.apply(apply(a, b, c, d));
    }
}

package ru.ifmo.ctddev.ml.core.interfaces;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Maxim Slyusarenko
 * @since 18.09.16
 */
@FunctionalInterface
public interface TriFunction<A, B, C, R> {

    R apply(A a, B b, C c);
}

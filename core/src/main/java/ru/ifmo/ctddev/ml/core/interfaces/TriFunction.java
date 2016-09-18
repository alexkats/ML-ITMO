package ru.ifmo.ctddev.ml.core.interfaces;

/**
 * @author Maxim Slyusarenko
 * @since 18.09.16
 */
@FunctionalInterface
public interface TriFunction<A, B, C, R> {

    R apply(A a, B b, C c);
}

package ru.ifmo.ctddev.ml.homework1.ui;

/**
 * @author Alexey Katsman
 * @since 18.09.16
 */

@SuppressWarnings("unused")
public class UIException extends Exception {
    public UIException() {
        this("Unknown");
    }

    public UIException(String message) {
        super("UI Exception occured! Reason: " + message);
    }
}

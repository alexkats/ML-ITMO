package ru.ifmo.ctddev.ml.core.ui;

/**
 * @author Alexey Katsman
 * @since 18.09.16
 */

@SuppressWarnings({"unused", "WeakerAccess"})
public class UIException extends Exception {
    public UIException() {
        this("Unknown");
    }

    public UIException(String message) {
        super("UI Exception occurred! Reason: " + message);
    }
}

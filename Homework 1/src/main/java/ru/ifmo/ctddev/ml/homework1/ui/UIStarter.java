package ru.ifmo.ctddev.ml.homework1.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alexey Katsman
 * @since 18.09.16
 */

public class UIStarter {

    private static boolean initialized = false;
    private final boolean success;
    private JFrame mainWindow;

    private UIStarter() {
        init();
        success = true;
    }

    private void init() {
        mainWindow = new JFrame();
        mainWindow.setMinimumSize(new Dimension(600, 600));
        mainWindow.setPreferredSize(new Dimension(600, 600));
        addSpinner();
        mainWindow.setVisible(true);
    }

    private void addSpinner() {
        List<String> options = Arrays.asList("Initial Data Set", "Predicted Data Set");
        JSpinner spinner = new JSpinner(new SpinnerListModel(options));
        mainWindow.add(spinner);
    }

    private void addTwoLayers() {

    }

    public static void start() throws UIException {
        if (initialized) {
            throw new UIException("Already opened");
        }

        UIStarter starter = new UIStarter();
        initialized = starter.success;
    }
}

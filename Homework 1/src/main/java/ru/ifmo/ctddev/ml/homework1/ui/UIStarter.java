package ru.ifmo.ctddev.ml.homework1.ui;

import ru.ifmo.ctddev.ml.core.entities.TwoDimensionalPoint;
import ru.ifmo.ctddev.ml.core.ui.UIException;
import ru.ifmo.ctddev.ml.homework1.DataSetEntity;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

/**
 * @author Alexey Katsman
 * @since 18.09.16
 */

public class UIStarter {

    private static boolean initialized = false;
    private final List<DataSetEntity> dataSet;
    private final List<DataSetEntity> predictedDataSet;
    private boolean success;
    private JFrame mainWindow;
    private JPanel cards;

    private UIStarter(List<DataSetEntity> dataSet, List<DataSetEntity> predictedDataSet) {
        this.dataSet = dataSet;
        this.predictedDataSet = predictedDataSet;

        SwingUtilities.invokeLater(() -> {
            init();
            success = true;
        });
    }

    private void init() {
        createAndConfigureMainWindow();
        addComboBox();
        addCardLayout();
        showMainWindow();
    }

    private void createAndConfigureMainWindow() {
        mainWindow = new JFrame();
        mainWindow.setPreferredSize(new Dimension(600, 600));
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addComboBox() {
        JComboBox<String> comboBox = new JComboBox<>(ComboBoxOptions.getStringOptions());

        comboBox.addItemListener(itemEvent -> {
            CardLayout cardLayout = (CardLayout) cards.getLayout();
            cardLayout.show(cards, (String) itemEvent.getItem());
        });

        mainWindow.getContentPane().add(comboBox, BorderLayout.PAGE_START);
    }

    private void addCardLayout() {
        cards = new JPanel(new CardLayout());
        cards.add(new ChartPanel1(), ComboBoxOptions.INITIAL.getName());
        cards.add(new ChartPanel2(), ComboBoxOptions.PREDICTED.getName());
        mainWindow.getContentPane().add(cards, BorderLayout.CENTER);
    }

    private TwoDimensionalPoint calcucatePoint(DataSetEntity entity, double width, double height) {
        TwoDimensionalPoint point = entity.getFeature();
        width -= 20.0d;
        height -= 20.0d;
        return new TwoDimensionalPoint(width * (point.getX() + 1.0d) / 2.0d, height * (point.getY() + 1.0d) / 2.0d);
    }

    private void showMainWindow() {
        mainWindow.pack();
        mainWindow.setLocationByPlatform(true);
        mainWindow.setVisible(true);
    }

    public static void start(List<DataSetEntity> dataSet, List<DataSetEntity> predictedDataSet) throws UIException {
        if (initialized) {
            throw new UIException("Already opened");
        }

        UIStarter starter = new UIStarter(dataSet, predictedDataSet);
        initialized = starter.success;
    }

    private class ChartPanel1 extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.clearRect(0, 0, getWidth(), getHeight());

            dataSet.forEach(entity -> {
                TwoDimensionalPoint pointInChart = calcucatePoint(entity, getWidth(), getHeight());
                g.setColor(entity.getEntityClass() == 0 ? Color.RED : Color.GREEN);
                g.fillOval((int) Math.round(pointInChart.getX()), (int) Math.round(pointInChart.getY()), 10, 10);
            });
        }
    }

    private class ChartPanel2 extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.clearRect(0, 0, getWidth(), getHeight());

            predictedDataSet.forEach(entity -> {
                TwoDimensionalPoint pointInChart = calcucatePoint(entity, getWidth(), getHeight());
                g.setColor(entity.getEntityClass() == 0 ? Color.RED : Color.GREEN);
                g.fillOval((int) Math.round(pointInChart.getX()), (int) Math.round(pointInChart.getY()), 10, 10);
            });
        }
    }

    private enum ComboBoxOptions {
        INITIAL("Initial Data Set"),
        PREDICTED("Predicted Data Set");

        String option;

        ComboBoxOptions(String option) {
            this.option = option;
        }

        public String getName() {
            return option;
        }

        public static String[] getStringOptions() {
            return new String[]{INITIAL.getName(), PREDICTED.getName()};
        }
    }
}

package ru.ifmo.ctddev.ml.homework2;

import lombok.Getter;
import ru.ifmo.ctddev.ml.common.MathUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxim Slyusarenko
 * @since 08.10.16
 */
public class DataSetNormalizator {

    private final List<DataSetEntity> dataSet;
    @Getter
    private double normalizationArea;
    @Getter
    private double normalizationRoom;
    @Getter
    private double normalizationPrice;

    public DataSetNormalizator(List<DataSetEntity> dataSet) {
        this.dataSet = dataSet;
    }

    public List<DataSetEntity> normalize() {
        double maxArea = -1;
        double maxRoom = -1;
        double maxPrice = -1;
        List<DataSetEntity> result = new ArrayList<>();
        for (DataSetEntity entity : dataSet) {
            if (MathUtils.isGreater(entity.getArea(), maxArea)) {
                maxArea = entity.getArea();
            }
            if (MathUtils.isGreater(entity.getRoom(), maxRoom)) {
                maxRoom = entity.getRoom();
            }
            if (MathUtils.isGreater(entity.getPrice(), maxPrice)) {
                maxPrice = entity.getPrice();
            }
        }
        normalizationArea = maxArea;
        normalizationRoom = maxRoom;
        normalizationPrice = maxPrice;
        for (DataSetEntity entity : dataSet) {
            result.add(new DataSetEntity(entity.getArea() / maxArea, entity.getRoom() / maxRoom, entity.getPrice() / maxPrice));
        }
        return result;
    }
}

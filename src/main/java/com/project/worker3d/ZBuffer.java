package com.project.worker3d;

import com.project.worker2d.Image;

import java.util.Arrays;

public class ZBuffer {
    private final double[][] matrix;

    public ZBuffer(Image image) {
        this.matrix = new double[image.height][image.width];
        for (double[] row : matrix)
            Arrays.fill(row, Double.MAX_VALUE);
    }

    public double[][] getMatrix() {
        return matrix;
    }
}

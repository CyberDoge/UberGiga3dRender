package com.project.worker2d;

import com.project.worker3d.Point3d;

import java.util.List;
import java.util.function.Function;

public class ImageCreate {
    private final Image image;

    public ImageCreate(Image image) {
        this.image = image;
    }

    public void createStar() {
        Function<Integer, Double> alpha = i -> 2 * Math.PI * i / 13;
        for (int i = 0; i < 16; i++) {
            var x = (int) Math.round(100 + 95 * Math.cos(alpha.apply(i)));
            var y = (int) Math.round(100 + 95 * Math.sin(alpha.apply(i)));
            line(x, y, image.width / 2, image.height / 2, image.matrix, Pixel.red());
        }
        image.save();
    }

    private void line(int x0, int y0, int x1, int y1, Pixel[][] matrix, Pixel pixel) {
        for (float t = 0; t < 1; t += 0.01){
            int x = (int) (x0 * (1 - t) + x1 * t);
            int y = (int) (y0 * (1 - t) + y1 * t);
            matrix[x][y] = pixel;
        }
    }

    public void create3dPoints(List<Point3d> points) {
        points.forEach(point1 -> {
            points.forEach(point2 -> {
                line(((int) (point1.y * 160)), ((int) (point1.x * 160)),
                        ((int) (point2.y * 160)), ((int) (point2.x * 160)), image.matrix, Pixel.red());
            });
        });
        image.save();
    }
}

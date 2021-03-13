package com.project.worker3d;

import com.project.worker2d.ImageCreate;
import com.project.worker2d.Pixel;

import java.util.Comparator;
import java.util.List;

public class Renderer3d {
    private final ImageCreate imageCreate;

    public Renderer3d(ImageCreate imageCreate) {
        this.imageCreate = imageCreate;
    }

    public void drawPolygon(List<Point3d> points, List<int[]> faces) {
        double minX = points.stream().min(Comparator.comparing((point) -> point.x)).get().x;
        double minY = points.stream().min(Comparator.comparing((point) -> point.y)).get().y;
        int k = 2500;
        points.forEach(point -> {
                    point.y = (point.y - minY) * k;
                    point.x = (point.x - minX) * k;
                }
        );
        for (int[] face : faces) {
            this.imageCreate.createRectangle(points.get(face[0]), points.get(face[1]), points.get(face[2]),
                    new Pixel((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        }
    }

}

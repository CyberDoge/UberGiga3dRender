package com.project.worker2d;

import com.project.store.Store;
import com.project.utils.MathFunctions;
import com.project.worker3d.Point3d;
import com.project.worker3d.ZBuffer;

import java.util.List;

public class Drawer {
    private final ZBuffer zBuffer;
    private Store store;

    public Drawer(Store store) {
        this.store = store;

        this.zBuffer = new ZBuffer(store.getImage());
    }

    public void draw3dPoints(List<Point3d> points) {
        points.forEach(point3d -> {
            store.getImage().matrix[(int) point3d.x][(int) point3d.y] = Pixel.red();
        });
    }

    private void line(double x0, double y0, double x1, double y1, Pixel[][] matrix, Pixel pixel) {
        for (float t = 0; t < 1; t += 0.0001) {
            int x = (int) (x0 * (1 - t) + x1 * t);
            int y = (int) (y0 * (1 - t) + y1 * t);
            matrix[x][y] = pixel;
        }
    }

    public void drawLines(List<Point3d> points, List<int[]> faces) {
        var matrix = store.getImage().matrix;
        for (int[] face : faces) {
            Thread first = new Thread(() -> {
                line(points.get(face[0]).x, points.get(face[0]).y, points.get(face[1]).x, points.get(face[1]).y, matrix, Pixel.red());
                line(points.get(face[1]).x, points.get(face[1]).y, points.get(face[2]).x, points.get(face[2]).y, matrix, Pixel.red());
                line(points.get(face[0]).x, points.get(face[0]).y, points.get(face[2]).x, points.get(face[2]).y, matrix, Pixel.red());
            });
            first.start();
        }
        store.getImage().save();
    }
    private void drawRectanglePixel(int x, int y, Point3d point1, Point3d point2, Point3d point3){
        if (this.zBuffer.getMatrix().length <= x || this.zBuffer.getMatrix()[x].length <= y) {
            return;
        }
        var cord = MathFunctions.countBarycentricCoordinate(new Point3d(x, y, 0), point1, point2, point3);
        if (!cord.isGreaterThen0()) {
            return;
        }
        double z = cord.lambda * point1.z
                + cord.lambda1 * point2.z
                + cord.lambda2 * point3.z;
        if (z <= this.zBuffer.getMatrix()[x][y]) {
            double cos1 = MathFunctions.cos(point1, new Point3d());
            double cos2 = MathFunctions.cos(point2, new Point3d());
            double cos3 = MathFunctions.cos(point3, new Point3d());
            store.getImage().matrix[x][y] = new Pixel(
                    ((int) (8550000 * (cord.lambda * cos1 + cord.lambda1 * cos2 + cord.lambda2 * cos3))), 0, 0
            );
            this.zBuffer.getMatrix()[x][y] = z;
        }
    }
    public void drawRectangle(Point3d point1, Point3d point2, Point3d point3) {
        double maxX = MathFunctions.max(point1.x, point2.x, point3.x);
        double minX = MathFunctions.min(point1.x, point2.x, point3.x);
        double maxY = MathFunctions.max(point1.y, point2.y, point3.y);
        double minY = MathFunctions.min(point1.y, point2.y, point3.y);

        for (int x = (int) minX; x < maxX; x++) {
            for (int y = (int) minY; y < maxY; y++) {
                drawRectanglePixel(x, y, point1, point2, point3);
            }
        }
    }
}

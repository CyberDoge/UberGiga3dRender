package com.project.worker2d;

import com.project.utils.MathFunctions;
import com.project.worker3d.Point3d;
import com.project.worker3d.ZBuffer;

import java.util.List;

public class Drawer {
    private final Image image;
    private final ZBuffer zBuffer;

    public Drawer(Image image) {
        this.image = image;
        this.zBuffer = new ZBuffer(image);
    }

    public void draw3dPoints(List<Point3d> points) {
        points.forEach(point3d -> {
            image.matrix[(int) point3d.x][(int) point3d.y] = Pixel.red();
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
        int i = 0;
        for (int[] face : faces) {
            Thread first = new Thread(() -> {
                line(points.get(face[0]).x, points.get(face[0]).y, points.get(face[1]).x, points.get(face[1]).y, image.matrix, Pixel.red());
                line(points.get(face[1]).x, points.get(face[1]).y, points.get(face[2]).x, points.get(face[2]).y, image.matrix, Pixel.red());
                line(points.get(face[0]).x, points.get(face[0]).y, points.get(face[2]).x, points.get(face[2]).y, image.matrix, Pixel.red());
            });
            first.start();
        }
        image.save();
    }

    public void drawRectangle(Point3d point1, Point3d point2, Point3d point3, Pixel pixel) {
        double maxX = MathFunctions.max(point1.x, point2.x, point3.x);
        double minX = MathFunctions.min(point1.x, point2.x, point3.x);
        double maxY = MathFunctions.max(point1.y, point2.y, point3.y);
        double minY = MathFunctions.min(point1.y, point2.y, point3.y);
        var matrix = this.image.matrix;
        for (int x = (int) minX; x < maxX; x++) {
            for (int y = (int) minY; y < maxY; y++) {
                var cord = MathFunctions.countBarycentricCoordinate(new Point3d(x, y, 0), point1, point2, point3);
                if (cord.isGreaterThen0()) {
                    double z = cord.lambda * point1.z
                            + cord.lambda1 * point2.z
                            + cord.lambda2 * point3.z;
                    if (z <= this.zBuffer.getMatrix()[x][y]) {
                        matrix[x][y] = pixel;
                        this.zBuffer.getMatrix()[x][y] = z;
                    }
                }
            }
        }
    }
}

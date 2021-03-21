package com.project.worker2d;

import com.project.utils.MathFunctions;
import com.project.worker3d.Point3d;
import com.project.worker3d.ZBuffer;

import java.util.Comparator;
import java.util.List;

public class ImageCreate {
    private final Image image;
    private final ZBuffer zBuffer;

    public ImageCreate(Image image) {
        this.image = image;
        this.zBuffer = new ZBuffer(image);
    }


    public void create3dPoints(List<Point3d> points) {
        double minX = points.stream().min(Comparator.comparing((point) -> point.x)).get().x;
        double minY = points.stream().min(Comparator.comparing((point) -> point.y)).get().y;
        int k = 2500;
        points.forEach(point ->
                image.matrix[((int) ((point.y - minY) * k))][(int) ((point.x - minX) * k)] = Pixel.red()
        );
    }

    public void createRectangle(Point3d point1, Point3d point2, Point3d point3, Pixel pixel) {
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

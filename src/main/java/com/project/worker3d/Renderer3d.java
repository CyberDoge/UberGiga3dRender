package com.project.worker3d;

import com.project.store.Store;
import com.project.utils.MathFunctions;
import com.project.worker2d.Drawer;
import com.project.worker2d.Pixel;
import org.ejml.simple.SimpleMatrix;

public class Renderer3d {
    private final Drawer drawer;
    private final ObjParser objParser;
    private final Store store;

    public Renderer3d(Store store, Drawer drawer, ObjParser objParser) {
        this.store = store;
        this.drawer = drawer;
        this.objParser = objParser;
    }

    public void draw() {
//        rotateModel();
        drawPolygons();
    }

    public void rotateModel() {
        double alpha = 1, betta = 1, gamma = 1;
        SimpleMatrix matrix1 = new SimpleMatrix(new double[][]{
                {1, 0, 0},
                {0, Math.cos(alpha), Math.sin(alpha)},
                {0, -Math.sin(alpha), Math.cos(alpha)},
        });
        SimpleMatrix matrix2 = new SimpleMatrix(new double[][]{
                {Math.cos(betta), 0, Math.sin(betta)},
                {0, 1, 0},
                {-Math.sin(betta), 0, Math.cos(betta)},
        });
        SimpleMatrix matrix3 = new SimpleMatrix(new double[][]{
                {Math.cos(gamma), Math.sin(gamma), 0},
                {-Math.sin(gamma), Math.cos(gamma), 0},
                {0, 0, 1},
        });

        SimpleMatrix R = matrix1.mult(matrix2).mult(matrix3);
        for (Point3d point : this.store.getPoints()) {
            var vector = new SimpleMatrix(new double[][]{
                    {point.x, point.y, point.z}
            }).transpose();
            var newPoint = R.mult(vector);
            point.update(newPoint.get(0), newPoint.get(1), newPoint.get(2));
        }
    }

    private void drawPolygons() {
        double maxCos = Double.MIN_VALUE;
        double minCos = Double.MAX_VALUE;
        for (Point3d point : this.store.getPoints()) {
            double cos = MathFunctions.cos(point, new Point3d());
            if (maxCos < cos) {
                maxCos = cos;
            } else if (minCos > cos) {
                minCos = cos;
            }
        }
        minCos = Math.max(minCos, 0);
        double redAlfa = 255 / maxCos;
        for (int[] face : this.store.getFaces()) {
            double cos = MathFunctions.cos(this.store.getPoints().get(face[0]), new Point3d(0, 0, 1));
            if (cos > 0) {
                this.drawer.drawRectangle(
                        this.store.getPoints().get(face[0]),
                        this.store.getPoints().get(face[1]),
                        this.store.getPoints().get(face[2]),
                        new Pixel((int) ((cos - minCos) * redAlfa), 0, 0)
                );
            }
        }
    }
}
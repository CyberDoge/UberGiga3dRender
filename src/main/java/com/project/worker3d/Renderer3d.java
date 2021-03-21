package com.project.worker3d;

import com.project.utils.MathFunctions;
import com.project.worker2d.ImageCreate;
import com.project.worker2d.Pixel;

public class Renderer3d {
    private final ImageCreate imageCreate;
    private final ObjParser objParser;

    public Renderer3d(ImageCreate imageCreate, ObjParser objParser) {
        this.imageCreate = imageCreate;
        this.objParser = objParser;
    }

    public void drawPolygon() {
        objParser.scalePoints();
        drawRectangle();
    }

    private void drawRectangle() {

        double maxCos = Double.MIN_VALUE;
        double minCos = Double.MAX_VALUE;
        for (Point3d point : this.objParser.getPoints()) {
            double cos = MathFunctions.cos(point, new Point3d(0, 0, 1));
            if (maxCos < cos) {
                maxCos = cos;
            } else if (minCos > cos) {
                minCos = cos;
            }
        }
        double redAlfa = 255 / maxCos;
        for (int[] face : this.objParser.getFaces()) {

            double cos = MathFunctions.cos(this.objParser.getPoints().get(face[0]), new Point3d(0, 0, 1));
            if (cos < 0) {
                this.imageCreate.createRectangle(
                        this.objParser.getPoints().get(face[0]),
                        this.objParser.getPoints().get(face[1]),
                        this.objParser.getPoints().get(face[2]),
                        new Pixel((int) ((cos - minCos) * redAlfa), 0, 0)
                );
            }
        }
    }
}
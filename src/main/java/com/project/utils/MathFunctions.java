package com.project.utils;

import com.project.worker3d.Point3d;

import java.util.Arrays;

public class MathFunctions {
    public static BarycentricCoordinate countBarycentricCoordinate(Point3d point, Point3d point0, Point3d point1, Point3d point2) {
        int x = (int) point.x;
        int y = (int) point.y;
        double x0 = point0.x;
        double x1 = point1.x;
        double x2 = point2.x;
        double y0 = point0.y;
        double y1 = point1.y;
        double y2 = point2.y;
        return countBarycentricCoordinate(x, y, x0, x1, x2, y0, y1, y2);
    }

    public static BarycentricCoordinate countBarycentricCoordinate(
            int x, int y, double x0, double x1, double x2, double y0, double y1, double y2) {

        double lambda0 = ((x1 - x2) * (y - y2) - (y1 - y2) * (x - x2)) / ((x1 - x2) * (y0 - y2) - (y1 - y2) * (x0 - x2));
        double lambda1 = ((x2 - x0) * (y - y0) - (y2 - y0) * (x - x0)) / ((x2 - x0) * (y1 - y0) - (y2 - y0) * (x1 - x0));
        double lambda2 = ((x0 - x1) * (y - y1) - (y0 - y1) * (x - x1)) / ((x0 - x1) * (y2 - y1) - (y0 - y1) * (x2 - x1));
        return new BarycentricCoordinate(lambda0, lambda1, lambda2);
    }

    public static double min(double... points) {
        double res = Arrays.stream(points).min().orElse(0);
        return res < 0 ? 0 : res;
    }

    public static double max(double... points) {
        return Arrays.stream(points).max().orElse(0);
    }

    public static double scalarMul1(Point3d point0, Point3d point1, Point3d point2) {
        return (point1.x - point0.x) * (point1.x - point2.x) + (point1.y - point0.y) * (point1.y - point2.y)
                + (point1.z - point0.z) * (point1.z - point2.z) + (point1.z - point0.z) * (point1.z - point2.z);
    }

    public static Point3d scalarMul(Point3d point0, Point3d point1, Point3d point2) {
        return new Point3d((point1.x - point0.x) * (point1.x - point2.x), (point1.y - point0.y) * (point1.y - point2.y),
                (point1.z - point0.z) * (point1.z - point2.z) + (point1.z - point0.z) * (point1.z - point2.z));
    }

    public static double cos(Point3d n, Point3d l) {
        return (n.x * l.x + n.y * l.y + n.z * l.z) /
                (Math.sqrt(n.x * n.x + n.y * n.y + n.z * n.z) * Math.sqrt(l.x * l.x + l.y * l.y + l.z * l.z));
    }
}

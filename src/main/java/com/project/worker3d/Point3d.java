package com.project.worker3d;

public class Point3d {
    public double x, y, z = 0;

    public Point3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3d() {
        this.x = 0;
        this.y = 0;
        this.z = 1;
    }

    public void update(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

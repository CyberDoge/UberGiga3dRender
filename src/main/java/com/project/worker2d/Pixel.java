package com.project.worker2d;

// oooo theris my mind
public class Pixel {
    public int r, g, b, alfa = 0;

    public Pixel(int r, int g, int b, int alfa) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.alfa = alfa;
    }

    public Pixel() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
        this.alfa = 255;
    }

    public Pixel(int r, int g, int b) {
        this.r = Math.abs(r);
        this.g = g;
        this.b = b;
    }

    public static Pixel red() {
        return new Pixel(255, 0, 0);
    }
    public Pixel white() {
        return new Pixel(255, 255, 255);

    }
}

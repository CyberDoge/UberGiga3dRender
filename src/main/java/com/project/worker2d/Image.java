package com.project.worker2d;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Image {
    public int width, height;
    public String path;

    public Pixel[][] matrix;

    public Image(int width, int height, String path) {
        this.width = width;
        this.height = height;
        this.path = path;
        this.colorToBlack();
    }

    public void colorToBlack() {
        if (matrix == null) {
            matrix = new Pixel[height][width];
        }
        for (int i = 0; i < matrix.length; i++) {
            var row = new Pixel[width];
            for (int j = 0; j < row.length; j++) {
                row[j] = new Pixel();
            }
            matrix[i] = row;
        }
    }

    public void save() {
        try {
            BufferedImage image = new BufferedImage(width, height, TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Pixel pixel = matrix[x][y];
                    Color newColor = new Color(pixel.r, pixel.g, pixel.b);
                    image.setRGB(x, height - 1 - y, newColor.getRGB());
                }
            }
            File output = new File(this.path);
            ImageIO.write(image, "png", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

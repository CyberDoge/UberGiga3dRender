package com.project.worker2d;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Image {
    public int width, height = 255;
    public String path;
    public Image() {
        path = "result.jpg";
    }
    public Pixel[][] matrix;

    public Image(int width, int height, String path) {
        this.width = width;
        this.height = height;
        this.path = path;
    }

    public void colorToBlack() {
        if(matrix == null){
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
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Pixel pixel = matrix[i][j];
                    Color newColor = new Color(pixel.r, pixel.g, pixel.b);
                    image.setRGB(i, j, newColor.getRGB());
                }
            }
            File output = new File(this.path);
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

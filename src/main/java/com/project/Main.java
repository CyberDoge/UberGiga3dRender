package com.project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");
        var scanner = new Scanner(System.in);
        System.out.print("H: ");
        final var H = 10; // scanner.nextInt();
        System.out.print("W: ");
        final var W =  10; //scanner.nextInt();
        short[][] matrix = new short[H][W];

        try {
            BufferedImage image = new BufferedImage(H, W, TYPE_INT_RGB);
            for(int i=0; i<H; i++) {
                for(int j=0; j< W; j++) {
                    int a = matrix[i][j];
                    Color newColor = new Color(a,a,a);
                    image.setRGB(j,i,newColor.getRGB());
                }
            }
            File output = new File("GrayScale.jpg");
            ImageIO.write(image, "jpg", output);
        }

        catch(Exception e) {
            System.out.println(e);
        }

    }
}

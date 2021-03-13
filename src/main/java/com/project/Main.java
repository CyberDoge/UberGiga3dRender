package com.project;

import com.project.worker2d.Image;
import com.project.worker2d.ImageCreate;
import com.project.worker3d.ObjParser;
import com.project.worker3d.Point3d;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");
        var scanner = new Scanner(System.in);
        System.out.print("H: ");
        final var H = 300; // scanner.nextInt();
        System.out.print("W: ");
        final var W = 300; // scanner.nextInt();
        var image = new Image(W, H, "result.jpg");
        image.colorToBlack();
        ImageCreate imageCreate = new ImageCreate(image);
        ObjParser parser = new ObjParser();
        parser.parseFile("test.obj");
        imageCreate.create3dPoints(parser.getPoints());
        image = new Image(W, H, "rect.png");
        image.colorToBlack();
        imageCreate.setImage(image);
        imageCreate.createRectangle(new Point3d(10, 10, 0), new Point3d(150, 50, 0), new Point3d(200, 20, 0));
        image = new Image(W, H, "rect1.png");
        image.colorToBlack();
        imageCreate.setImage(image);
        imageCreate.createRectangle(new Point3d(-100, 10, 0), new Point3d(150, 299, 0), new Point3d(299, 10, 0));

    }
}

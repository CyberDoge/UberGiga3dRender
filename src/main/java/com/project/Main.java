package com.project;

import com.project.worker2d.Image;
import com.project.worker2d.ImageCreate;
import com.project.worker3d.ObjParser;

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
    }
}

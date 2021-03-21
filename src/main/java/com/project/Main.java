package com.project;

import com.project.worker2d.Image;
import com.project.worker2d.ImageCreate;
import com.project.worker3d.ObjParser;
import com.project.worker3d.Renderer3d;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.print("H: ");
        final var H = 300; // scanner.nextInt();
        System.out.print("W: ");
        final var W = 300; // scanner.nextInt();
        var image = new Image(W, H, "crol.png");
        Renderer3d renderer3d = new Renderer3d(new ImageCreate(image), new ObjParser("crol.obj"));
        renderer3d.drawPolygon();
        image.save();
    }
}

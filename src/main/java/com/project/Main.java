package com.project;

import com.project.store.Store;
import com.project.worker2d.Drawer;
import com.project.worker2d.Image;
import com.project.worker3d.ObjParser;
import com.project.worker3d.Renderer3d;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.print("H: ");
        final var H = 5300; // scanner.nextInt();
        System.out.print("W: ");
        final var W = 5300; // scanner.nextInt();
        Store store = new Store();
        String name = "crol";
        var image = new Image(W, H, name + ".png");
        store.setImage(image);
        ObjParser objParser = new ObjParser(store, name + ".obj");
        Renderer3d renderer3d = new Renderer3d(store, new Drawer(store));
        renderer3d.draw();
        image.save();
    }
}

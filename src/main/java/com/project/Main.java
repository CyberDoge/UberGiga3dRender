package com.project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");
        var scanner = new Scanner(System.in);
        System.out.print("H: ");
        final var H = 200; // scanner.nextInt();
        System.out.print("W: ");
        final var W = 200; // scanner.nextInt();
        var image = new Image(W, H, "result.jpg");
        ImageCreate imageCreate = new ImageCreate(image);
        imageCreate.createStar();
    }
}

package com.project.worker3d;

import com.project.store.Store;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjParser {

    private final Store store;

    public ObjParser(Store store, String file) {
        this.store = store;
        this.parseFile(file);
    }


    public void parseFile(String file) {
        try {
            List<String> lines = Files.readAllLines(Path.of(file)).stream()
                    .filter(line -> !line.startsWith("#")).collect(Collectors.toList());
            List<Point3d> points = parsePoints(lines);
            List<int[]> faces = parseFaces(lines);

            scalePoints(points);

            store.setFaces(faces);
            store.setPoints(points);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<int[]> parseFaces(List<String> lines){
        return lines.stream().filter(line -> line.startsWith("f ")).map(line -> {
            String[] data = line.split("\\s+");
            data = Stream.of(data).map(d -> d.split("/")[0]).toArray(String[]::new);
            return new int[]{
                    Integer.parseInt(data[1]) - 1, Integer.parseInt(data[2]) - 1, Integer.parseInt(data[3]) - 1
            };
        }).collect(Collectors.toList());
    }
    private  List<Point3d> parsePoints(List<String> lines) {
        return lines.stream().filter(line -> line.startsWith("v ")).map(line -> {
            String[] data = line.split("\\s+");
            return new Point3d(Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]));
        }).collect(Collectors.toList());
    }
    private void scalePoints(List<Point3d> points) {
        double minX = points.stream().min(Comparator.comparing((point) -> point.x)).get().x;
        double minY = points.stream().min(Comparator.comparing((point) -> point.y)).get().y;
        int k = 50;
//        int k = 2500;
        points.forEach(point -> {
                    point.y = (point.y - minY) * k;
                    point.x = (point.x - minX) * k;
                }
        );
    }
}

package com.project.worker3d;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObjParser {
    private List<Point3d> points;

    public List<Point3d> getPoints() {
        return points == null ? new ArrayList<>() : this.points;
    }

    public void parseFile(String file) {
        try {
            List<String> lines = Files.readAllLines(Path.of(file)).stream()
                    .filter(line -> !line.startsWith("#")).collect(Collectors.toList());
            this.points = lines.stream().filter(line -> line.startsWith("v")).map(line -> {
                String[] data = line.split("\\s+");
                return new Point3d(Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3]));
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

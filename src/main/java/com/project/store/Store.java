package com.project.store;

import com.project.worker2d.Image;
import com.project.worker3d.Point3d;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private List<Point3d> points;
    private List<int[]> faces;
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Point3d> getPoints() {
        return points == null ? new ArrayList<>() : this.points;
    }

    public void setPoints(List<Point3d> points) {
        this.points = points;
    }

    public List<int[]> getFaces() {
        return faces == null ? new ArrayList<>() : this.faces;
    }

    public void setFaces(List<int[]> faces) {
        this.faces = faces;
    }
}

package com.project;

import java.util.Arrays;
import java.util.function.Function;

public class ImageCreate {
  private final Image image;

  public ImageCreate(Image image) {
    this.image = image;
  }

  public void createStar() {
    Pixel[][] matrix = new Pixel[image.height][image.width];
    for (int i = 0; i < matrix.length; i++) {
      var row = new Pixel[image.width];
      for (int j = 0; j < row.length; j++) {
        row[j] = new Pixel();
      }
      matrix[i] = row;
    }
    Function<Integer, Double> alpha =  i ->  2 * Math.PI * i / 13;
    for (int i = 0; i < 16; i++) {
      var x = (int) Math.round(100 + 95 * Math.cos(alpha.apply(i)));
      var y = (int)Math.round(100 + 95 * Math.sin(alpha.apply(i)));
      line(x, y, 100, 100, matrix);
    }

    image.matrix = matrix;
    image.save();
  }
  private void line(int x0, int y0, int x1, int y1, Pixel[][] matrix){
    for (float t = 0; t < 1; t+=0.001){
      int x = (int)(x0*(1-t) + x1*t);
      int y =(int) (y0*(1-t) + y1*t);
      matrix[x][y].r = 255;
    }
  }
}

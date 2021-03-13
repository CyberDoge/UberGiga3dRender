package com.project.utils;

public class BarycentricCoordinate {
    public double lambda1, lambda2, lambda;

    public BarycentricCoordinate(double lambda1, double lambda2, double lambda) {
        this.lambda1 = lambda1;
        this.lambda2 = lambda2;
        this.lambda = lambda;
    }

    public boolean isGreaterThen0() {
        return this.lambda > 0 && this.lambda1 > 0 && this.lambda2 > 0;
    }
}

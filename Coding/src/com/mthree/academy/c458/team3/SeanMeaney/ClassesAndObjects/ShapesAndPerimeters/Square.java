package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ShapesAndPerimeters;

class Square extends Shape {
    double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * 4;
    }
}

package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ShapesAndPerimeters;

class Circle extends Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getPerimeter() {
        return Math.PI * radius * radius;
    }
}

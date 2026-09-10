package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ShapesAndPerimeters;

abstract class Shape {

    String colour;

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return this.colour;
    }

    public abstract double getArea();

    public abstract double getPerimeter();

}

package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui;

//defines the methods that must be implemented to interact with the user
public interface UserIO {

    void print(String message);

    String readString(String prompt);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt,float min, float max);

    float readInt(String prompt);

    float readInt(String prompt,int min, int max);

    float readLong(String prompt);

    float readLong(String prompt,long min, long max);


}

package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.service;

public class ClassRosterDataValidationException extends Exception {

    public ClassRosterDataValidationException(String message) {
        super(message);
    }

    public ClassRosterDataValidationException(String message,
                                              Throwable cause) {
        super(message, cause);
    }

}

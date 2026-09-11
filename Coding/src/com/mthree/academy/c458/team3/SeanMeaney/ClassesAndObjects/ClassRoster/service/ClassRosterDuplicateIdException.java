package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.service;

public class ClassRosterDuplicateIdException extends Exception {

    public ClassRosterDuplicateIdException(String message) {
        super(message);
    }

    public ClassRosterDuplicateIdException(String message,
                                           Throwable cause) {
        super(message, cause);
    }

}


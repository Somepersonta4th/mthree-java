package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.service;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dao.ClassRosterPersistenceException;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dto.Student;

import java.util.List;

public interface ClassRosterServiceLayer {

    void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException;

    List<Student> getAllStudents() throws
            ClassRosterPersistenceException;

    Student getStudent(String studentId) throws
            ClassRosterPersistenceException;

    Student removeStudent(String studentId) throws
            ClassRosterPersistenceException;

}

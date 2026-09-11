package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.controller;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dao.ClassRosterDao;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dao.ClassRosterDaoException;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dto.Student;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.ClassRosterView;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIO;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIOConsoleImpl;

import java.util.List;

public class ClassRosterController {
    private ClassRosterView rosterView;
    private ClassRosterDao rosterDao;

    private UserIO io = new UserIOConsoleImpl();

    public ClassRosterController() {}

    public ClassRosterController (ClassRosterView rosterView, ClassRosterDao rosterDao) {
        this.rosterView = rosterView;
        this.rosterDao = rosterDao;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        }catch (ClassRosterDaoException e) {
            rosterView.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return rosterView.printMenuAndGetSelection();
    }

    private void createStudent() throws ClassRosterDaoException {
        rosterView.displayCreateStudentBanner();
        Student newStudent = rosterView.getNewStudentInfo();
        rosterDao.addStudent(newStudent.getStudentId(), newStudent);
        rosterView.displayCreateSuccessBanner();
    }

    private void listStudents() throws ClassRosterDaoException  {
        rosterView.displayDisplayAllBanner();
        List<Student> studentList = rosterDao.getAllStudents();
        rosterView.displayStudentList(studentList);
    }

    private void viewStudent() throws ClassRosterDaoException  {
        rosterView.displayDisplayStudentBanner();
        String studentId = rosterView.getStudentIdChoice();
        Student student = rosterDao.getStudent(studentId);
        rosterView.displayStudent(student);
    }

    private void removeStudent() throws ClassRosterDaoException  {
        rosterView.displayRemoveStudentBanner();
        String studentId = rosterView.getStudentIdChoice();
        Student removedStudent = rosterDao.removeStudent(studentId);
        rosterView.displayRemoveResult(removedStudent);
    }

    private void unknownCommand() {
        rosterView.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        rosterView.displayExitBanner();
    }

}

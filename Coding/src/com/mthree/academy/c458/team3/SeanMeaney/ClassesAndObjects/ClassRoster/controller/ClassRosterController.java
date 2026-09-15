package controller;

import dao.ClassRosterPersistenceException;
import dto.Student;
import service.ClassRosterDataValidationException;
import service.ClassRosterDuplicateIdException;
import service.ClassRosterServiceLayer;
import ui.ClassRosterView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

import java.util.List;

public class ClassRosterController {
    private ClassRosterView rosterView;
    private ClassRosterServiceLayer service;

    private UserIO io = new UserIOConsoleImpl();

    public ClassRosterController() {}

    public ClassRosterController (ClassRosterView rosterView, ClassRosterServiceLayer service) {
        this.rosterView = rosterView;
        this.service = service;
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
        }catch (ClassRosterPersistenceException e) {
            rosterView.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return rosterView.printMenuAndGetSelection();
    }

    private void createStudent() throws ClassRosterPersistenceException {
        rosterView.displayCreateStudentBanner();
        boolean hasErrors = false;
        do {
            Student currentStudent = rosterView.getNewStudentInfo();
            try {
                service.createStudent(currentStudent);
                rosterView.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                rosterView.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void listStudents() throws ClassRosterPersistenceException {
        List<Student> studentList = service.getAllStudents();

        rosterView.displayStudentList(studentList);
    }

    private void viewStudent() throws ClassRosterPersistenceException {
        String studentId = rosterView.getStudentIdChoice();
        Student student = service.getStudent(studentId) ;
        rosterView.displayStudent(student);
    }

    private void removeStudent() throws ClassRosterPersistenceException {
        rosterView.displayRemoveStudentBanner();
        String studentId = rosterView.getStudentIdChoice();
        service.removeStudent(studentId);
        //rosterView.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        rosterView.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        rosterView.displayExitBanner();
    }

}

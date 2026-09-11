package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.controller;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dao.ClassRosterDao;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.ClassRosterView;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIO;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIOConsoleImpl;

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
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    io.print("LIST STUDENTS");
                    break;
                case 2:
                    io.print("CREATE STUDENT");
                    break;
                case 3:
                    io.print("VIEW STUDENT");
                    break;
                case 4:
                    io.print("REMOVE STUDENT");
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");
    }

    private int getMenuSelection() {
        return rosterView.printMenuAndGetSelection();
    }

}

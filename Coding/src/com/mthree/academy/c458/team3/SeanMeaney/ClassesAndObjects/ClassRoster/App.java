package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.controller.ClassRosterController;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dao.ClassRosterDao;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dao.ClassRosterDaoFileImpl;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.ClassRosterView;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIO;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIOConsoleImpl;

public class App {

    private UserIO io = new UserIOConsoleImpl();
    private ClassRosterView consoleView = new ClassRosterView(io);
    private ClassRosterDao fileDao = new ClassRosterDaoFileImpl();

    public void run() {
        ClassRosterController controller = new ClassRosterController(consoleView, fileDao);
        controller.run();
    }

    public static void main(String[] args) {
        App application = new App();
        application.run();
    }

}

package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.controller.ClassRosterController;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dao.ClassRosterDao;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dao.ClassRosterDaoFileImpl;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.service.ClassRosterServiceLayer;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.service.ClassRosterServiceLayerImpl;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.ClassRosterView;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIO;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIOConsoleImpl;

public class App {

    public void run() {
        UserIO io = new UserIOConsoleImpl();
        ClassRosterView consoleView = new ClassRosterView(io);
        ClassRosterDao fileDao = new ClassRosterDaoFileImpl();
        ClassRosterServiceLayer service = new ClassRosterServiceLayerImpl(fileDao);

        ClassRosterController controller = new ClassRosterController(consoleView,service);
        controller.run();
    }

    public static void main(String[] args) {
        App application = new App();
        application.run();
    }

}

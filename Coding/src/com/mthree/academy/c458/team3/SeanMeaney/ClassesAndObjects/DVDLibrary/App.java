package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIO;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIOConsoleImpl;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.controller.DVDController;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao.DVDDao;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao.DVDDaoFileImpl;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao.DVDPersistenceException;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.service.DVDServiceLayer;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.service.DVDServiceLayerImpl;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.ui.DVDView;

public class App {
    public static void main(String[] args) throws DVDPersistenceException {
        UserIO io = new UserIOConsoleImpl();
        DVDDao dao = new DVDDaoFileImpl();
        DVDView view = new DVDView(io);
        DVDServiceLayer serviceLayer = new DVDServiceLayerImpl(dao);
        DVDController controller = new DVDController(view,serviceLayer);

        controller.run();
    }
}

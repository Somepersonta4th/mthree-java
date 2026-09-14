package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.controller;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dto.Student;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.service.ClassRosterDataValidationException;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.service.ClassRosterDuplicateIdException;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIO;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIOConsoleImpl;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao.DVDDao;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao.DVDPersistenceException;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dto.DVD;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.service.DVDServiceLayer;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.ui.DVDView;

public class DVDController {
    private DVDView view;
    private DVDServiceLayer serviceLayer;
    private UserIO io = new UserIOConsoleImpl();

    public DVDController (DVDView view, DVDServiceLayer serviceLayer) {
        this.view = view;
        this.serviceLayer = serviceLayer;
    }

    public void run() throws DVDPersistenceException {
        boolean isRunning = true;

        while (isRunning) {
            switch (getMenuSelection()) {
                case 1:
                    listDVDs();
                    break;
                case 2:
                    addDVD();
                    break;
                case 3:
                    viewDVD();
                    break;
                case 4:
                    removeDVD();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
            }
        }
    }

    private void removeDVD() throws DVDPersistenceException {
        view.displayRemoveDVDBanner();
        String title = view.getDVDTitleChoice();
        serviceLayer.removeDVD(title);
    }

    private void viewDVD() throws DVDPersistenceException {
        view.displayDVD(serviceLayer.getDVD(view.getDVDIdChoice()));
    }

    private void addDVD() {
        view.displayAddDVDBanner();
        boolean hasErrors = false;
        do {
            DVD dvd = view.getNewDVDInfo();
            try {
                serviceLayer.addDVD(dvd);
                view.displayAddSuccessBanner();
                hasErrors = false;
            } catch (DVDPersistenceException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void listDVDs() throws DVDPersistenceException {
        view.displayDVDList(serviceLayer.getAllDVD());
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    public DVDController () {}
}

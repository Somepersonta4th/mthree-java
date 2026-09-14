package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.service;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao.DVDDao;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao.DVDPersistenceException;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dto.DVD;

import java.util.List;

public class DVDServiceLayerImpl implements DVDServiceLayer {

    private DVDDao dao;

    public DVDServiceLayerImpl(DVDDao dao) {
        this.dao = dao;
    }

    @Override
    public void addDVD(DVD dvd) throws DVDPersistenceException {
        dao.addDVD(dvd.getTitle(),dvd);
    }

    @Override
    public List<DVD> getAllDVD() throws DVDPersistenceException {
        return dao.getAllDVD();
    }

    @Override
    public DVD getDVD(String title) throws DVDPersistenceException {
        return dao.getDVD(title);
    }

    @Override
    public DVD removeDVD(String title) throws DVDPersistenceException {
        return dao.removeDVD(title);
    }
}

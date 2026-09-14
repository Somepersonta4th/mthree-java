package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.service;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao.DVDPersistenceException;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dto.DVD;

import java.util.List;

public interface DVDServiceLayer {

    void addDVD (DVD dvd) throws DVDPersistenceException;

    List<DVD> getAllDVD () throws DVDPersistenceException;

    DVD getDVD (String title) throws DVDPersistenceException;

    DVD removeDVD(String title) throws DVDPersistenceException;

}

package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dto.DVD;

import java.util.List;

public interface DVDDao {

    DVD addDVD (String title, DVD dvd) throws DVDPersistenceException;

    DVD removeDVD (String title) throws DVDPersistenceException;

    DVD getDVD (String title) throws DVDPersistenceException;

    List<DVD> getAllDVD () throws DVDPersistenceException;

}

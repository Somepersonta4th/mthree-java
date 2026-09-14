package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dto.DVD;

import java.util.List;

public interface DVDDao {

    DVD addDVD (String title, DVD dvd);

    DVD removeDVD (String title);

    DVD getDVD (String title);

    List<DVD> getAllDVD ();

}

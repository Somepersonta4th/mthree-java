package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.service;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dto.DVD;

import java.util.List;

public interface DVDServiceLayer {

    void addDVD ();

    List<DVD> getAllDVD ();

    DVD getDVD ();

    DVD removeDVD();

}

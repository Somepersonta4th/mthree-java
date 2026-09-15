package com.mthree.academy.c458.team3.SeanMeaney.testing.ClassesAndObjects.DVDLibrary;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao.DVDDao;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao.DVDDaoFileImpl;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao.DVDPersistenceException;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dto.DVD;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileWriter;

class DVDDaoFileImplTest {

    private DVDDao dao;
    private String testPath = "src/com/mthree/academy/c458/team3/SeanMeaney/testing/ClassesAndObjects/DVDLibrary/testfile";

    /* testing plan

    **


     */


    DVD aDVD = new DVD("aDVD");
    DVD bDVD = new DVD("bDVD");

    @BeforeEach
    public void setUp() throws Exception{
        new FileWriter(testPath);
        dao = new DVDDaoFileImpl(testPath);
    }

    @Test
    public void addDVD () throws DVDPersistenceException {
        dao.addDVD(aDVD.getTitle(), aDVD);
    }

}
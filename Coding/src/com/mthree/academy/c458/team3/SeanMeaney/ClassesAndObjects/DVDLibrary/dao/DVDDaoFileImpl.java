package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dao;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dao.ClassRosterPersistenceException;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dto.DVD;

import java.io.*;
import java.util.*;

public class DVDDaoFileImpl implements DVDDao{

    private Map<String, DVD> dvds = new HashMap<>();
    public static final String ROSTER_FILE = "src/com/mthree/academy/c458/team3/SeanMeaney/ClassesAndObjects/DVDLibrary/dvd.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDPersistenceException {
        loadDVDs();
        DVD newDVD = dvds.put(title, dvd);
        writeDVDs();
        return newDVD;
    }

    @Override
    public DVD removeDVD(String title) throws DVDPersistenceException {
        loadDVDs();
        DVD dvd = dvds.remove(title);
        writeDVDs();
        return dvd;
    }

    @Override
    public DVD getDVD(String title) throws DVDPersistenceException {
        loadDVDs();
        return dvds.get(title);
    }

    @Override
    public List<DVD> getAllDVD() throws DVDPersistenceException {
        loadDVDs();
        return new ArrayList<>(dvds.values());
    }

    private void loadDVDs () throws DVDPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDPersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }

        String line;
        DVD currentDVD;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            currentDVD = unmarshalDVD(line);
            dvds.put(currentDVD.getTitle(),currentDVD);
        }

        scanner.close();
    }

    private void writeDVDs () throws DVDPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new DVDPersistenceException(
                    "Could not save student data.", e);
        }

        String asText;

        List<DVD> dvdList = this.getAllDVD();

        for (DVD dvd : dvdList) {
            asText = marshalDVD(dvd);
            out.println(asText);
            out.flush();
        }

        out.close();

    }

    private DVD unmarshalDVD (String raw) {
        String[] tokens = raw.split(DELIMITER);
        String title = tokens[0];
        DVD dvd = new DVD(title);
        dvd.setReleaseDate(tokens[1]);
        dvd.setMPAARating(tokens[2]);
        dvd.setDirector(tokens[3]);
        dvd.setStudio(tokens[4]);
        dvd.setUserRating(tokens[5]);
        dvd.setUserNote(tokens[6]);
        return dvd;
    }

    private String marshalDVD (DVD dvd) {
        String asText = dvd.getTitle() + DELIMITER +
        dvd.getReleaseDate() + DELIMITER +
        dvd.getMPAARating() + DELIMITER +
        dvd.getDirector() + DELIMITER +
        dvd.getStudio() + DELIMITER +
        dvd.getUserRating() + DELIMITER +
        dvd.getUserNote();
        return asText;
    }
}

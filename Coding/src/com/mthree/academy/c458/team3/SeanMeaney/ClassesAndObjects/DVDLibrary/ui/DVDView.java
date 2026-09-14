package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.ui;

import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.dto.Student;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui.UserIO;
import com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.DVDLibrary.dto.DVD;

import java.util.List;

public class DVDView {

    private UserIO io;

    public DVDView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List dvds");
        io.print("2. Create New dvd");
        io.print("3. View a dvd");
        io.print("4. Remove a dvd");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getDirector());
            io.print(dvd.getReleaseDate());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public String getDVDIdChoice() { return io.readString("Please enter the DVD title.");    }

    public void displayDVDList(List<DVD> allDVD) {
        for (DVD currentStudent : allDVD) {
            String studentInfo = String.format("#%s : %s %s",
                    currentStudent.getTitle(),
                    currentStudent.getDirector(),
                    currentStudent.getReleaseDate());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayAddDVDBanner() {io.print("=== New DVD ===");}

    public void displayAddSuccessBanner() {io.readString("Student successfully created.  Please hit enter to continue");}

    public void displayErrorMessage(String message) {
        io.print("=== ERROR ===");
        io.print(message);
    }

    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter release date");
        String director = io.readString("Please enter director name");
        String MPAARating = io.readString("Please enter MPAA rating");
        String studio = io.readString("Please enter studio name");
        String userRating = io.readString("Please enter user's rating");
        String userNote = io.readString("Please enter user's note");
        DVD dvd = new DVD(title);
        dvd.setStudio(studio);
        dvd.setDirector(director);
        dvd.setReleaseDate(releaseDate);
        dvd.setMPAARating(MPAARating);
        dvd.setUserNote(userNote);
        dvd.setUserRating(userRating);
        return dvd;
    }

    public void displayRemoveDVDBanner() {io.print("=== Remove DVD ===");}

    public String getDVDTitleChoice() {return io.readString("Please enter the DVD title.");}
}

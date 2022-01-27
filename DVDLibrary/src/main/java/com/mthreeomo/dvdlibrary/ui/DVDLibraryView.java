/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthreeomo.dvdlibrary.ui;

import com.mthreeomo.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author okeow
 */
public class DVDLibraryView {
    
    private UserIO io;
    
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List the DVDs");
        io.print("2. Add a new DVD");
        io.print("3. View a DVD");
        io.print("4. Remove a DVD");
        io.print("5. Edit DVD Information");
        io.print("6. Search for DVD by Title");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    public DVD getNewDVDInfo() { // For the 2nd use case
        String title = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter release date");
        String mpaaRating = io.readString("Please enter MPAA rating");
        String directorName = io.readString("Please enter Director's Name");
        String studio = io.readString("Please enter studio name");
        String userRating = io.readString("Please enter User rating");
        
        DVD currentDVD = new DVD(title);
        
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        
        return currentDVD;
    }
    
    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created.  Please hit enter to continue");
    }
    
    public void displayDVDList(List<DVD> dvdList) { // For the 1st use case
        for (DVD currentDVD : dvdList) {
            String dvdInfo = String.format("%s, directed by %s \nReleased %s by %s \nRated %s",
                  currentDVD.getTitle(),
                  currentDVD.getDirectorName(),
                  currentDVD.getReleaseDate(),
                  currentDVD.getStudio(),
                  currentDVD.getMpaaRating());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
    
    // 3rd use case
    public void displayDisplayDVDBanner () {
        io.print("=== Display DVD ===");
    }

    public String getTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMpaaRating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such DVD is in the Library.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if(dvdRecord != null){
          io.print("DVD successfully removed.");
        }else{
          io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayEditDVDBanner () {
        io.print("=== Edit DVD ===");
    }
    
    public String getTitleEditChoice() {
        return io.readString("Please enter the DVD Title.");
    }
    
    public DVD editDVDInfo(String title) { // For the 5th use case
        
        DVD dvd = new DVD(title);
        if (title != null) {
            
            String releaseDate = io.readString("Please enter new release date");
            String mpaaRating = io.readString("Please enter new MPAA rating");
            String directorName = io.readString("Please enter new Director's Name");
            String studio = io.readString("Please enter new studio name");
            String userRating = io.readString("Please enter new User rating");

            

            dvd.setReleaseDate(releaseDate);
            dvd.setMpaaRating(mpaaRating);
            dvd.setDirectorName(directorName);
            dvd.setStudio(studio);
            dvd.setUserRating(userRating);
            
            
        }
        else {
            io.print("No such DVD is in the Library.");
        }
        
        return dvd;
    }
    
    public void displayEditSuccessBanner() {
        io.readString("DVD successfully edited.  Please hit enter to continue");
    }
    
    // the 6th use case
    public void displaySearchDVDBanner () {
        io.print("=== Search DVD ===");
    }

    public String getSearchChoice() {
        return io.readString("Please enter the DVD Title.");
    }

    public void searchDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle() + " is available");
            io.print("");
        } else {
            io.print("No such DVD is in the Library.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}

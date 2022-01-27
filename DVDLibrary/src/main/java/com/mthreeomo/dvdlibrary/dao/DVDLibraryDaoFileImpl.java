/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthreeomo.dvdlibrary.dao;

import com.mthreeomo.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author okeow
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadLibrary();
        DVD newDVD = dvds.put(title, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException{
        loadLibrary();
        return new ArrayList(dvds.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException{
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException{
        loadLibrary();
        DVD removedDVD = dvds.remove(title);
        writeLibrary();
        return removedDVD;
    }

    @Override
    public DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException{
        loadLibrary();
        DVD editedDVD = dvds.replace(title, dvd);
        writeLibrary();
        return editedDVD;
    }

    @Override
    public DVD searchDVD(String title) throws DVDLibraryDaoException{
        loadLibrary();
        return dvds.get(title);
    }
    
    private Map<String, DVD> dvds = new HashMap<>();
    
    // File Persistence, Marshalling and Unmarshalling
    private DVD unmarshallDVD(String dvdAsText){
        
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        
        String title = dvdTokens[0];
        
        DVD dvdFromFile = new DVD(title);
        
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        
        dvdFromFile.setDirectorName(dvdTokens[3]);
        
        dvdFromFile.setStudio(dvdTokens[4]);
        
        dvdFromFile.setUserRating(dvdTokens[5]);
        
        return dvdFromFile;
    }
    
    private void loadLibrary() throws DVDLibraryDaoException {
        
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        
        String currentLine;
        
        DVD currentDVD;
        
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVD
            currentDVD = unmarshallDVD(currentLine);

            // We are going to use the title as the map key for our dvd object.
            // Put currentDVD into the map using title as the key
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();

    }
    
    private String marshallDVD(DVD aDVD){
        
        String dvdAsText = aDVD.getTitle() + DELIMITER;
        
        // ReleaseDate
        dvdAsText += aDVD.getReleaseDate() + DELIMITER;

        // MPAARating
        dvdAsText += aDVD.getMpaaRating() + DELIMITER;
        
        // DirectorName
        dvdAsText += aDVD.getDirectorName() + DELIMITER;
        
        // Studio
        dvdAsText += aDVD.getStudio() + DELIMITER;

        // UserRating - don't forget to skip the DELIMITER here.
        dvdAsText += aDVD.getUserRating();

        // We have now turned a student to text! Return it!
        return dvdAsText;
    }
    
    private void writeLibrary() throws DVDLibraryDaoException {
        
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save student data.", e);
        }
        
        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {
            // turn a Student into a String
            dvdAsText = marshallDVD(currentDVD);
            // write the Student object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthreeomo.dvdlibrary.dao;

import com.mthreeomo.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author okeow
 */
public interface DVDLibraryDao {
    
    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;  // param dvd is the title to be added to the library. Use case 2
    
    List<DVD> getAllDVDs() throws DVDLibraryDaoException; // Use case 1
    
    DVD getDVD(String title) throws DVDLibraryDaoException; // Use case 3, view a DVD from the collection
    
    DVD removeDVD(String title) throws DVDLibraryDaoException; // Use case 4 Remove dvd from collection
    
    DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException; // 5th use case
    
    DVD searchDVD(String title) throws DVDLibraryDaoException; // 6th use case. Model this use case after the list dvd use case
    
}

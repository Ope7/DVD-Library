/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthreeomo.dvdlibrary.controller;

import com.mthreeomo.dvdlibrary.dao.DVDLibraryDao;
import com.mthreeomo.dvdlibrary.dao.DVDLibraryDaoException;
import com.mthreeomo.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.mthreeomo.dvdlibrary.dto.DVD;
import com.mthreeomo.dvdlibrary.ui.DVDLibraryView;
import java.util.List;

/**
 *
 * @author okeow
 */
public class DVDLibraryController {
    
    private DVDLibraryView view;
    
    private DVDLibraryDao dao;
    
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVDs();
                        break;
                    case 2:
                        createDVD();
                        break;
                    case 3:
                        viewDVD();
                        break;
                    case 4:
                        removeDVD();
                        break;
                    case 5:
                        editDVD();
                        break;
                    case 6:
                        findDVD();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        }
        catch (DVDLibraryDaoException e) {
             view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createDVD() throws DVDLibraryDaoException{
        view.displayCreateDVDBanner(); // Just a banner
        DVD newDVD = view.getNewDVDInfo();  // Obtains from the user(and then creates) the new dvd info and stores them in currentDVD (which newDVD is now = to)
        dao.addDVD(newDVD.getTitle(), newDVD);  // This then does the storing in dvds
        view.displayCreateSuccessBanner();  // Just a banner
    }
    
    private void listDVDs() throws DVDLibraryDaoException{
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    private void viewDVD() throws DVDLibraryDaoException{
        view.displayDisplayDVDBanner();
        String title = view.getTitleChoice(); // This gets the movie name from the user
        DVD dvd = dao.getDVD(title); // this uses that title to obtain the rest of the info on that dvd
        view.displayDVD(dvd);
    }
    
    private void removeDVD() throws DVDLibraryDaoException{
        view.displayRemoveDVDBanner();
        String title = view.getTitleChoice();  // This gets the movie name from the user (title)
        DVD removedDVD = dao.removeDVD(title); // this uses that title to identify and remove the DVD
        view.displayRemoveResult(removedDVD);
    }
    
    private void editDVD() throws DVDLibraryDaoException{
        view.displayEditDVDBanner();
        String title = view.getTitleEditChoice(); // This gets the dvd name from the user (title)
        DVD dvd = view.editDVDInfo(title);  // This obtains info from the user
        dao.editDVD(title, dvd);        // this replaces that entry
        
        view.displayEditSuccessBanner();
               
    }
    
    private void findDVD() throws DVDLibraryDaoException{
        view.displaySearchDVDBanner();
        String title = view.getSearchChoice(); // This gets the movie name from the user
        DVD dvd = dao.getDVD(title); // this uses that title to obtain the rest of the info on that dvd
        view.searchDVD(dvd);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}

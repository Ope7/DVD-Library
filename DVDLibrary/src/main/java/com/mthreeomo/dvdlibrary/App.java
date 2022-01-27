/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthreeomo.dvdlibrary;

import com.mthreeomo.dvdlibrary.controller.DVDLibraryController;
import com.mthreeomo.dvdlibrary.dao.DVDLibraryDao;
import com.mthreeomo.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.mthreeomo.dvdlibrary.ui.DVDLibraryView;
import com.mthreeomo.dvdlibrary.ui.UserIO;
import com.mthreeomo.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author okeow
 */
public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }
}

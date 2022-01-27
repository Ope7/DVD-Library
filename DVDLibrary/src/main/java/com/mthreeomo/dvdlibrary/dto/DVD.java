/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthreeomo.dvdlibrary.dto;

/**
 *
 * @author okeow
 */
public class DVD { // Here we create the DVD class and list its properties
    
    private String title;
    private String releaseDate;
    // date/month/year
    private String mpaaRating;
    private String directorName;
    // First and last name
    private String studio;
    private String userRating;

    public DVD(String title) { // title is the primary field here
        this.title = title;
    }
// Now to set up the accessors and mutators (getters and setters)
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {    // I'm including a setter for titles also
        this.title = title;                 // in case it's also part of the edits
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    
    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }
    
    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
}

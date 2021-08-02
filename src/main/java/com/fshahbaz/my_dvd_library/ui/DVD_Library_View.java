
package com.fshahbaz.my_dvd_library.ui;

import com.fshahbaz.my_dvd_library.dto.DVD;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

//This class handles all the UI/IO logic.
public class DVD_Library_View {
    
    //DEPENDENCY INJECTION
    //Declaring UserIo 
    private UserIO io;
    
    //Implementing constructor to take IO as parameter
    public DVD_Library_View(UserIO io) {
        this.io = io;
    }
    
    //Prints the menu option, gets called in Controller
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add DVD to the Collection");
        io.print("2. Remove a DVD from the collection");
        io.print("3. Edit information for an existing DVD");
        io.print("4. List the DVDs");
        io.print("5. Display information for particular DVD");
        io.print("6. Search for DVD");
        io.print("7. Exit");

        return io.readInt("Please select from the"
                    + " above choices.", 1, 7);
    }
    
    //Retrives information when add DVD is called, and creates DVD
    public DVD getDVDInfo() {
        String movieTitle = io.readString("Please enter Movie Title");
        String releaseDate = io.readString("Please enter the Release Date");
        String mpaaRating = io.readString("Please enter MPAA Rating");
        String directorName = io.readString("Please enter Director's Name");
        String studio = io.readString("Please enter the Studio");
        String userRating = io.readString("Please enter User Rating or any additional notes on movie");
        //Creating new DVD object
        DVD currentDVD = new DVD(movieTitle);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        return currentDVD;
    }
    
    //Simple Banner for add DVD
    public void displayCreateDVDBanner() {
        io.print("=== Add DVD ===");
    }
    
    //Simple Banner when DVD is created
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully added.  Please hit enter to continue");
    }
    
    //Takes a list of DVD objects as a parameter and displays the information for
    //each DVD to the screen
    public void displayDVDList(List<DVD> dvdList) {
        boundary();
        for (DVD currentDVD : dvdList) {
            String studentInfo = String.format("Title: %s\n"
                    + "Release Date: %s\n"
                    + "MPAA Rating: %s\n"
                    + "Director's Name: %s\n"
                    + "Studio: %s\n"
                    + "User Rating: %s",
                  currentDVD.getTitle(),
                  currentDVD.getReleaseDate(),
                  currentDVD.getMpaaRating(),
                  currentDVD.getDirectorName(),
                  currentDVD.getStudio(),
                  currentDVD.getUserRating());
                  
            io.print(studentInfo);
            boundary();
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Display DVDs Banner
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVD's ===");
    }
    
    //Display DVD banner for specific DVD
    public void displayDisplayDVDBanner() {
        io.print("=== Display DVD ===");
    }

    //Retrives/Returns Title of DVD
    public String getDVDTitleChoice() {
        return io.readString("Please enter the DVD Title: ");
    }

    //Displays specifc DVD if it's in the collection,
    //If not, returns "No Such DVD"
    public void displayDVD(DVD currentDVD) {
        if (currentDVD != null) {
            boundary();
            io.print("Title: " + currentDVD.getTitle());
            io.print("Release Date: " + currentDVD.getReleaseDate());
            io.print("MPAA Rating: " + currentDVD.getMpaaRating());
            io.print("Director's Name: " + currentDVD.getDirectorName());
            io.print("Studio: " + currentDVD.getStudio());
            io.print("User Rating: " + currentDVD.getUserRating());
            boundary();
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Banner for Removing DVD
    public void displayRemoveDVDBanner(){
        io.print("=== Remove DVD ===");
    }

    //Checks to see if attempted removed DVD is 
    //in collection of not
    public void displayRemoveResult(DVD dvd) {
        if(dvd != null){
          io.print("DVD successfully removed.");
        }else{
          io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Banner for editing DVD
    public void displayEditDVDBanner () {
        io.print("=== Edit Student ===");
    }
    
    //Checks to see if DVD is contained in collection
    public boolean displayEditResult(DVD dvd) {
        if(dvd != null){
          io.print(dvd.getTitle() + " Selected");
          return true;
        }
          io.print("No such DVD.");
          io.readString("Please hit enter to continue.");
          return false;
    }
    
    //Retrives title of DVD that needs to be edited
    public String getDVDEditTitleChoice() {
        return io.readString("Please enter the DVD Title You want to Edit: ");
    }
    
    //Displays Edit Menu Options
    public void editMenu(){
        io.print("=== Edit Options ===");
        io.print("1. Edit Release Date");
        io.print("2. Edit MPAA Rating");
        io.print("3. Edit Director's Name");
        io.print("4. Edit Studio");
        io.print("5. Edit User Rating");
    }
    
    //Retrives specific DVD feild that needs to be edited, 
    //returns value back to controller
    public int editHelper(){
        int editChoice = 0;
        int input = io.readInt("Please enter which movie option you want to change: ");
        switch (input) {
            case 1:
                io.print("Release Date");
                editChoice = 1;
                break;
            case 2:
                io.print("MPAA Rating");
                editChoice = 2;
                break;
            case 3:
                io.print("Director's Name");
                editChoice = 3;
                break;
            case 4:
                io.print("Studio");
                editChoice = 4;
                break;
            case 5:
                io.print("User Rating");
                editChoice = 5;
                break;
            default:
                break;
        }
        return editChoice;
    }
    
    //Retrives and return new edit value for DVD
    public String getEditValue(int editChoice){
        String replace = null;
        switch (editChoice) {
            case 1:
                replace = io.readString("What would you like the new Release Date to be?");
                break;
            case 2:
                replace = io.readString("What would you like the new MPAA Rating to be?");
                break;
            case 3:
                replace = io.readString("What would you like the new Director's Name to be?");
                break;
            case 4:
                replace = io.readString("What would you like the new Studio Name to be?");
                break;
            case 5:
                replace = io.readString("What would you like the new User Rating to be?");
                break;
            default:
                break;
        }
        return replace;
    }
    
    //Displays message if DVD has been edited
    public void displaySuccessEdit(){
        io.print("DVD successfully edited.  Please hit enter to continue");
    }
    
    //Banner displayed for Search DVD option
    public void displaySearchBanner() {
        io.print("=== Search DVD ===");
    }

    //Displays specific message if DVD is in collection or not
    public void searchDvdResult(DVD dvd) {
        if (dvd != null) {
            io.print("DVD is stored in system");
            boundary();
        } else {
            io.print("DVD is NOT stored in system");
        }
    }
    
    //Display message when application terminates
    public void displayExitBanner(){
        io.print("Good Bye!!!");
    }

    //Display message for invalid command
    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }
    
    //Display message for Error
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    //Boundary design used for displaying information
    public void boundary(){
        io.print("=======================");
    }
    
    
    
    
    
}

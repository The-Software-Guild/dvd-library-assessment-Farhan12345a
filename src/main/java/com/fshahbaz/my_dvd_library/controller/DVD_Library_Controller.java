/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.my_dvd_library.controller;

import com.fshahbaz.my_dvd_library.dao.DvdDaoException;
import com.fshahbaz.my_dvd_library.dto.DVD;
import com.fshahbaz.my_dvd_library.ui.DVD_Library_View;
import com.fshahbaz.my_dvd_library.ui.UserIO;
import com.fshahbaz.my_dvd_library.ui.UserIOConsoleImpl;
import java.util.List;
import java.util.ArrayList;
import com.fshahbaz.my_dvd_library.dao.DVD_LibraryDao;

//This is the orchestrator of the application. It knows what needs
//to be done, when it needs to be done, and what component can do the job.
public class DVD_Library_Controller {
   
    //private UserIO io = new UserIOConsoleImpl();
    //Just initializing declarations (Dependency Injection)
    private DVD_Library_View view;
    private DVD_LibraryDao dao;
    
    //DEPENDENCY INJECTION: initalizing members via constructor
    //Make View's dependency injected into constructor.
    //App class WILL DO INJECTING
    public DVD_Library_Controller(DVD_LibraryDao dao, DVD_Library_View view) {
        this.dao = dao;
        this.view = view;
    }
    
    //Displaying menu
    //The run method will ask for the user selection and then 
    //route the request to a private controller method.
    public void run() {
        boolean flag = true;
        int menuSelection = 0;
        try{
            while (flag) {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        createDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        listDVDs();
                        break;
                    case 5:
                        viewDVD();
                        break;
                    case 6:
                        searchDVD();
                        break;
                    case 7:
                        flag = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        exitMessage();
        } catch (DvdDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    //Retrives Menu selection from view
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    //Display the Create banner, retrives information, 
    //stores in DVD object, and displays success banner
    private void createDVD() throws DvdDaoException{
        view.displayCreateDVDBanner();
        DVD newStudent = view.getDVDInfo();
        dao.addDVD(newStudent.getTitle(),newStudent);
        view.displayCreateSuccessBanner();
    }
    
    //Display the List DVDs banner, 
    //stores DVD objects in a list, and displays each DVD
    private void listDVDs() throws DvdDaoException{
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVD();
        view.displayDVDList(dvdList);
    }
    
    //Display the Specifc DVD Banner, Retrives Title,
    //Gets specific DVD pertaining to title, and displays the DVD
    private void viewDVD() throws DvdDaoException{
        view.displayDisplayDVDBanner();
        String title = view.getDVDTitleChoice();
        DVD dvd = dao.getDVD(title);
        view.displayDVD(dvd);
    }
    
    //Display the Remove DVD Banner, Retrives Title,
    //Gets specific DVD pertaining to title, and DVD from collection
    private void removeDVD() throws DvdDaoException{
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDTitleChoice();
        DVD removedDVD = dao.removeDVD(dvdTitle);
        view.displayRemoveResult(removedDVD);
    }
    
    private void editDVD() throws DvdDaoException{
        view.displayEditDVDBanner();
        String title = view.getDVDEditTitleChoice();
        DVD editDVD = dao.getDVD(title);
        boolean result2 = view.displayEditResult(editDVD);
        if(result2){
            view.editMenu();
            int choice = view.editHelper();
            String newVal = view.getEditValue(choice);
            dao.editDVD(editDVD, choice, newVal);
            view.displaySuccessEdit();
        }
    }
    
    //Display the Search DVD Banner, Retrives Title,
    //Gets specific DVD pertaining to title, Displays if DVD is 
    //in collection or not and information
    private void searchDVD() throws DvdDaoException {
        view.displaySearchBanner();
        String title = view.getDVDTitleChoice();
        DVD dvd = dao.searchDvd(title);
        view.searchDvdResult(dvd);
        view.displayDVD(dvd);
    }
    
    
    //Displays message for unknown input
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    //Displays message for project termination
    private void exitMessage() {
        view.displayExitBanner();
    }
    
}
    


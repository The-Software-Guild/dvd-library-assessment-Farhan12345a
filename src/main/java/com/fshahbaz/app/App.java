/*
 * Name: Farhan Shahbaz
 * Date: July 30th, 2021
 * Project Name:
 * Description: 
 */
package com.fshahbaz.app;
import com.fshahbaz.my_dvd_library.controller.DVD_Library_Controller;
import com.fshahbaz.my_dvd_library.dao.DVD_LibraryDaoImpl;
import com.fshahbaz.my_dvd_library.ui.DVD_Library_View;
import com.fshahbaz.my_dvd_library.ui.UserIO;
import com.fshahbaz.my_dvd_library.ui.UserIOConsoleImpl;
import com.fshahbaz.my_dvd_library.dao.DVD_LibraryDao;

public class App {
    public static void main(String[]  args){
        
        //Dependency Injection below
        //(From constructors created in View and Controller) to
        //handle specific implementations of interfaces
        UserIO myIo = new UserIOConsoleImpl();
        DVD_Library_View myView = new DVD_Library_View(myIo);
        DVD_LibraryDao myDao = new DVD_LibraryDaoImpl();
        //Instantiating our controller and calling the run method
        DVD_Library_Controller controller = new DVD_Library_Controller(myDao, myView);
        controller.run();
    }
}



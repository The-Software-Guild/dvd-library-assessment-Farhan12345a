
package com.fshahbaz.my_dvd_library.dao;

//This is the error class for our application. It extends Exception.
public class DvdDaoException extends Exception {
    public DvdDaoException(String message) {
        super(message);
    }
    
    public DvdDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}


package com.fshahbaz.my_dvd_library.dao;

import com.fshahbaz.my_dvd_library.dto.DVD;
import java.util.List;


public interface DVD_LibraryDao {
    
    /**
     * Adds the given DVD to the collection and associates it with the given
     * title. 
     *
     * @param title title associated with DVD
     * @param dvd DVD to be added to collection
     * @return the DVD object previously associated with the given  
     * title if it exists, null otherwise
     */
    DVD addDVD(String title, DVD dvd) throws DvdDaoException;

    /**
     * Removes from the collection the DVD associated with title
     *
     * @param title id of DVD to be removed
     * @return DVD object that was removed or null if no DVD
     * was associated with the given title
     */
    DVD removeDVD(String title) throws DvdDaoException;
    
    /**
     * Edits the given DVD to the collection and associates it with the given
     * title. 
     *
     * @param editDVD DVD that is going to be edited
     * @param choice which feature of DVD that's going to change
     * @param replace the new value
     * @return the DVD object previously associated with the given  
     * title if it exists, null otherwise
     */
    public int editDVD(DVD editDVD, int choice, String replace) throws DvdDaoException;
    
    /**
     * Returns a List of all DVDs in the collection.
     * @return List containing all DVDs in the collection.
     */
    List<DVD> getAllDVD() throws DvdDaoException;
    
    /**
     * Returns the DVD object associated with the given title,
     * and true/false if it's in collection
     *
     * @param title of specific DVD
     * @return the DVD object associated with the given title,  
     * null if no such DVD exists
     */
    DVD searchDvd(String title) throws DvdDaoException;

    /**
     * Returns the DVD object associated with the given title
     *
     * @param title of specific DVD
     * @return the DVD object associated with the given title,  
     * null if no such DVD exists
     */
    DVD getDVD(String title) throws DvdDaoException;

    
}

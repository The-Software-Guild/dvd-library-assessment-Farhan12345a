package com.fshahbaz.my_dvd_library.dao;

import com.fshahbaz.my_dvd_library.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

//Holds all DVD data in memory
public class DVD_LibraryDaoImpl implements DVD_LibraryDao{
    
    //Using map for quick/easy look-up
    private Map<String, DVD> dvds = new HashMap<>();
    
    //File Name
    public static final String ROSTER_FILE = "dvd_data.txt";
    public static final String DELIMITER = "::";
    
    @Override
    public DVD addDVD(String title, DVD dvd) throws DvdDaoException {
        loadRoster();
        DVD newDVD = dvds.put(title, dvd);
        writeRoster();
        return newDVD;
    }

    @Override
    public DVD removeDVD(String title) throws DvdDaoException{
        loadRoster();
        DVD removedDVD = dvds.remove(title);
        writeRoster();
        return removedDVD;
    }

    @Override
    public int editDVD(DVD editDVD, int choice, String replace) throws DvdDaoException{
        loadRoster();
        int result = 0;
        DVD edit = editDVD;
  
        switch (choice) {
            case 1:
                edit.setReleaseDate(replace);
                dvds.replace(edit.getTitle(), edit);
                result = 1;
                break;
            case 2:
                edit.setMpaaRating(replace);
                dvds.replace(edit.getTitle(), edit);
                result = 2;
                break;
            case 3:
                edit.setDirectorName(replace);
                dvds.replace(edit.getTitle(), edit);
                result = 3;
                break;
            case 4:
                edit.setStudio(replace);
                dvds.replace(edit.getTitle(), edit);
                result = 4;
                break;
            case 5:
                edit.setUserRating(replace);
                dvds.replace(edit.getTitle(), edit);
                result = 5;
                break;
            default:
                break;
        }
        writeRoster();
        return result;
    }
    

    @Override
    public List<DVD> getAllDVD() throws DvdDaoException {
        loadRoster();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD searchDvd(String title) throws DvdDaoException {
        return dvds.get(title);
    }

    @Override
    public DVD getDVD(String title) throws DvdDaoException {
        loadRoster();
        return dvds.get(title);
    }
    
    private DVD unmarshallDVD(String dvdAsText){
        //Takes in String to break apart
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String title = dvdTokens[0];
        DVD dvdFromFile = new DVD(title);
        //Set specific feilds
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserRating(dvdTokens[5]);
    
        // We have now created a DVD! Return it!
        return dvdFromFile;
    }
    
    //Method reads collection into memory
    private void loadRoster() throws DvdDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdDaoException("-_- Could not load roster data into memory.", e);
        }
        
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        DVD currentDVD;
        // Go through dvd_data.txt line by line, decoding each line into a 
        // DVD object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVD
            currentDVD = unmarshallDVD(currentLine);

            // We are going to use the title as the map key for our DVD object.
            // Put currentDVD into the map using title as the key
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }
    
    //Marshall method organizes DVD information from memory into line of
    //text. So that it can be written properly into a file
    private String marshallDVD(DVD aDVD){
        //Add items into string with delimiter seperating values
        String dvdAsText = aDVD.getTitle() + DELIMITER;
        dvdAsText += aDVD.getReleaseDate() + DELIMITER;
        dvdAsText += aDVD.getMpaaRating() + DELIMITER;
        dvdAsText += aDVD.getDirectorName() + DELIMITER;
        dvdAsText += aDVD.getStudio() + DELIMITER;
        //Delimeter is skipped for last item
        dvdAsText += aDVD.getUserRating();

        return dvdAsText;
    }

    //Method writes the student information from memory to a fule
    private void writeRoster() throws DvdDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new DvdDaoException("Could not save student data.", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVD();
        for (DVD currentDVD : dvdList) {
            // turn a DVD into a String
            dvdAsText = marshallDVD(currentDVD);
            // write the DVD object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
    
}

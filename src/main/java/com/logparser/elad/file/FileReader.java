package com.logparser.elad.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

/**
 * Created by eladw on 3/25/17.
 */
public class FileReader {

    private String filename;
    private LineIterator currentLine;


    public FileReader(String filename){
        try {
            currentLine = FileUtils.lineIterator(new File(filename), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNextLine() {

        try {
            if (currentLine.hasNext()) {
                return currentLine.nextLine();
            } else {
                //close file
                LineIterator.closeQuietly(currentLine);
                return "eladw done";
            }
        } catch (Exception e){
            return null; //todo: handle the error case
        }
    }


}

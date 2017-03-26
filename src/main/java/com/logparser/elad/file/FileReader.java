package com.logparser.elad.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by eladw on 3/25/17.
 * Read lines form file, line after line.
 */
public class FileReader {

    private static final Logger logger = LoggerFactory.getLogger(FileReader.class);
    public static final String ERROR_READ = "ERROR-READ";

    private String filename;
    private LineIterator currentLine;


    public FileReader(String filename){
        try {
            this.filename = filename;
            currentLine = FileUtils.lineIterator(new File(filename), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return Empty in case of EOF.
     *        ERROR_READ in case of exception.
     *        string line in case of noraml read.
     */
    public Optional<String> getNextLine() {

        try {
            if (currentLine.hasNext()) {
                String line =  currentLine.nextLine();
                logger.debug(line);
                return Optional.of(line);

            } else {
                logger.info("No more lines to read");
                return Optional.empty();
            }
        } catch (Exception e){
            return Optional.of(ERROR_READ); //todo: handle the error case
        }
    }

    public void closeFile(){
        //close file
        LineIterator.closeQuietly(currentLine);
    }


    public String getFilename() {
        return filename;
    }
}

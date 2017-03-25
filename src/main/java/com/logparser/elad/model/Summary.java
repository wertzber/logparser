package com.logparser.elad.model;

/**
 * Created by eladw on 3/25/17.
 * Summary of data
 */
public class Summary {

    private long numOfReadRows = 0;

    public long getNumOfReadRows() {
        return numOfReadRows;
    }

    public void enlargeNumOfReadRows() {
        this.numOfReadRows++;

    }

}

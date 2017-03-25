package com.logparser.elad.model;

import com.logparser.elad.stats.BrowserStats;
import com.logparser.elad.stats.OsStats;

/**
 * Created by eladw on 3/25/17.
 * Summary of data
 */
public class Summary {

    private long numOfReadRows = 0;
    private OsStats osStats;
    private BrowserStats browserStats;

    public Summary(){
        osStats = new OsStats();
        browserStats = new BrowserStats();

    }


    public long getNumOfReadRows() {
        return numOfReadRows;
    }

    public void enlargeNumOfReadRows() {
        this.numOfReadRows++;

    }

    public OsStats getOsStats() {
        return osStats;
    }

    public BrowserStats getBrowserStats() {
        return browserStats;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Summary{");
        sb.append("numOfReadRows=").append(numOfReadRows);
        sb.append(", osStats=").append(osStats);
        sb.append(", browserStats=").append(browserStats);
        sb.append('}');
        return sb.toString();
    }
}

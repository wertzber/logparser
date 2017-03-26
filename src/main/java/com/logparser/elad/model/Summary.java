package com.logparser.elad.model;

import com.logparser.elad.stats.BrowserStats;
import com.logparser.elad.stats.IPStats;
import com.logparser.elad.stats.OsStats;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by eladw on 3/25/17.
 * Summary of data
 */
public class Summary {

    private AtomicLong numOfReadRows = new AtomicLong(0);
    private AtomicLong numOfHandleRows = new AtomicLong(0);

    private OsStats osStats;
    private BrowserStats browserStats;
    private IPStats ipStats;

    public Summary(){
        osStats = new OsStats();
        browserStats = new BrowserStats();
        ipStats = new IPStats();

    }


    public long getNumOfReadRows() {
        return numOfReadRows.get();
    }

    public void enlargeNumOfReadRows() {
        this.numOfReadRows.incrementAndGet();

    }

    public OsStats getOsStats() {
        return osStats;
    }

    public long getNumOfHandleRows() {
        return numOfHandleRows.get();
    }

    public long addNumOfHandleRows() {
        return this.numOfHandleRows.incrementAndGet();
    }

    public BrowserStats getBrowserStats() {
        return browserStats;
    }

    public IPStats getIpStats() {
        return ipStats;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Summary{");
        sb.append("numOfReadRows=").append(numOfReadRows);
        sb.append(", numOfHandleRows=").append(numOfHandleRows);
        sb.append(", osStats=").append(osStats);
        sb.append(", browserStats=").append(browserStats);
        sb.append('}');
        return sb.toString();
    }
}

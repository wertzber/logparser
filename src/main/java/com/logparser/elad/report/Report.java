package com.logparser.elad.report;

import com.logparser.elad.model.Summary;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by eladw on 3/26/17.
 */
public class Report {

    public String generateReport(Summary summary){
        StringBuilder sb = new StringBuilder();
        AtomicLong total = new AtomicLong(0);
        summary.getOsStats().getOsStatsMap().forEach((k,v)->total.incrementAndGet(v));

    }

}

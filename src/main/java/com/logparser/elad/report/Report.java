package com.logparser.elad.report;

import com.logparser.elad.model.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by eladw on 3/26/17.
 */
public class Report {

    private static final Logger logger = LoggerFactory.getLogger(Report.class);

    public static String generateReport(Summary summary){
        StringBuilder sb = new StringBuilder();
        AtomicLong totalOs = new AtomicLong(0);
        summary.getOsStats().getOsStatsMap().forEach((k,v)->totalOs.accumulateAndGet(v.longValue(),
                (newNum,oldNum)-> newNum + oldNum ));
        sb.append("Total OS stats:").append(totalOs).append("\n");
        summary.getOsStats().getOsStatsMap().forEach((k, v) -> {
            sb.append(k).append(":").append(String.format("%.2f",  (v.longValue() / (double)totalOs.longValue())))
                    .append("\n");
        });
        sb.append("================");


        AtomicLong totalBrowser = new AtomicLong(0);
        summary.getBrowserStats().getBrowserStatsMap().forEach((k, v) -> totalBrowser.accumulateAndGet(v.longValue(),
                (newNum, oldNum) -> newNum + oldNum));
        sb.append("Total Browser stats:").append(totalBrowser).append("\n");
        summary.getBrowserStats().getBrowserStatsMap().forEach((k, v) -> {
            sb.append(k).append(":").append(String.format("%.2f", (v.longValue() / (double) totalBrowser.longValue())))
                    .append("\n");
        });
        sb.append("================");

        AtomicLong totalIps = new AtomicLong(0);
        summary.getIpStats().getIpStatsMap().forEach((k, v) -> totalIps.accumulateAndGet(v.longValue(),
                (newNum, oldNum) -> newNum + oldNum));
        sb.append("Total IP stats:").append(totalIps).append("\n");
        summary.getIpStats().getIpStatsMap().forEach((k, v) -> {
            sb.append(k).append(":").append(String.format("%.6f", (v.longValue() / (double) totalIps.longValue())))
                    .append("\n");
        });

        return sb.toString();
    }

}

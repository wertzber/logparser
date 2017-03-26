package com.logparser.elad.report;

import com.logparser.elad.model.Result;
import com.logparser.elad.model.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

/**
 * Created by eladw on 3/26/17.
 * Report the total percentage.
 */
public class Report {

    private static final Logger logger = LoggerFactory.getLogger(Report.class);

    private static Comparator<Result> byCount =
            (Result o1, Result o2)-> o2.getCount() - o1.getCount();

    public static String generateReport(Summary summary){
        StringBuilder sb = new StringBuilder();
        AtomicLong totalOs = new AtomicLong(0);
        summary.getOsStats().getOsStatsMap().forEach((k,v)->totalOs.accumulateAndGet(v.longValue(),
                (newNum,oldNum)-> newNum + oldNum ));
        sb.append("Total OS stats:").append(totalOs).append("\n");

        createSortReport(summary.getOsStats().getOsStatsMap(), sb, totalOs);

        sb.append("================");

        AtomicLong totalBrowser = new AtomicLong(0);
        summary.getBrowserStats().getBrowserStatsMap().forEach((k,v)->totalBrowser.accumulateAndGet(v.longValue(),
                (newNum,oldNum)-> newNum + oldNum ));
        sb.append("Total Browser stats:").append(totalOs).append("\n");
        createSortReport(summary.getBrowserStats().getBrowserStatsMap(), sb, totalBrowser);

        sb.append("================");

        AtomicLong totalIps = new AtomicLong(0);
        summary.getIpStats().getIpStatsMap().forEach((k, v) -> totalIps.accumulateAndGet(v.longValue(),
                (newNum, oldNum) -> newNum + oldNum));
        sb.append("Total IP stats:").append(totalIps).append("\n");
        createSortReport(summary.getIpStats().getIpStatsMap(), sb, totalIps);
        sb.append("================");

        return sb.toString();
    }

    private static void createSortReport(Map<String,LongAdder> map, StringBuilder sb, AtomicLong total) {
        List<Result> results = new ArrayList<>();

        Set<Map.Entry<String, LongAdder>> set = map
                .entrySet();

        for(Map.Entry<String, LongAdder> entry : set){
            Result r = new Result(entry.getKey(),entry.getValue().intValue());
            results.add(r);
        }
        results.stream().sorted(byCount).forEach(result -> sb.append(result.getKey()
        ).append(":  ").append(String.format("%.2f", (result.getCount() / (double) total.intValue())))
                .append("%\n"));
    }

}

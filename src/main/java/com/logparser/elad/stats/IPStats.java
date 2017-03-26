package com.logparser.elad.stats;

import com.logparser.elad.model.UAParserFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by eladw on 3/26/17.
 */
public class IPStats {

    private static final Logger logger = LoggerFactory.getLogger(IPStats.class);

    private ConcurrentHashMap<String,LongAdder> ipStats;

    public IPStats(){
        ipStats = new ConcurrentHashMap<>();
    }

    public void calcIPStatistics(String ip){
        try {
                ipStats.computeIfAbsent(ip, k -> new LongAdder()).increment();

        } catch (Exception e) {
            logger.error("failed to calculate ip statistics ", e );
        }

    }

    public void printIpStats(){
        logger.info(ipStats.toString());
    }

    public ConcurrentHashMap<String, LongAdder> getIpStatsMap() {
        return ipStats;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IPStats{");
        sb.append("ipStats=").append(ipStats);
        sb.append('}');
        return sb.toString();
    }
}

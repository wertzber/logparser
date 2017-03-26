package com.logparser.elad.stats;

import com.logparser.elad.model.UAParserFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by eladw on 3/26/17.
 */
public class OsStats {

    private static final Logger logger = LoggerFactory.getLogger(OsStats.class);

    private Map<String,LongAdder> osStats;

    public OsStats(){
        osStats = new ConcurrentHashMap<>();
    }

    public void calcStatistics(UAParserFields uaParserFields){
        try {
            if(uaParserFields != null && !uaParserFields.getOsName().isEmpty() && uaParserFields.getOsName().length()> 1){
                osStats.computeIfAbsent(uaParserFields.getOsName(), k -> new LongAdder()).increment();
            }
        } catch (Exception e) {
            logger.error("failed to calculate os statistics ", e );
        }
        if(logger.isDebugEnabled()){
            logger.debug(osStats.toString());
        }

    }

    public void printOsStats(){
        logger.info(osStats.toString());
    }

    public Map<String, LongAdder> getOsStatsMap() {
        return osStats;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OsStats{");
        sb.append("osStats=").append(osStats);
        sb.append('}');
        return sb.toString();
    }
}

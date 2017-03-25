package com.logparser.elad.stats;

import com.logparser.elad.model.UAParserFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by eladw on 3/26/17.
 */
public class OsStats {

    private static final Logger logger = LoggerFactory.getLogger(OsStats.class);

    private ConcurrentHashMap<String,LongAdder> osStats;

    public OsStats(){
        osStats = new ConcurrentHashMap<>();
    }

    public void calOsStatistics(UAParserFields uaParserFields){
        try {
            if(uaParserFields != null && !uaParserFields.getOsName().isEmpty()){
                osStats.computeIfAbsent(uaParserFields.getOsName(), k -> new LongAdder()).increment();
            }
        } catch (Exception e) {
            logger.error("failed to calculate os statistics ", e );
        }

    }

    public void printOsStats(){
        logger.info(osStats.toString());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OsStats{");
        sb.append("osStats=").append(osStats);
        sb.append('}');
        return sb.toString();
    }
}

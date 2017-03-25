package com.logparser.elad.stats;

import com.logparser.elad.model.UAParserFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by eladw on 3/26/17.
 */
public class BrowserStats {

    private static final Logger logger = LoggerFactory.getLogger(BrowserStats.class);

    private ConcurrentHashMap<String,LongAdder> browserStats;

    public BrowserStats(){
        browserStats = new ConcurrentHashMap<>();
    }

    public void calBrowserStatistics(UAParserFields uaParserFields){
        try {
            if(uaParserFields != null && !uaParserFields.getBrowser().isEmpty()) {
                browserStats.computeIfAbsent(uaParserFields.getOsName(), k -> new LongAdder()).increment();
            }
        } catch (Exception e) {
            logger.error("failed to calculate os statistics ", e );
        }

    }

    public void printBrowserStats(){
        logger.info(browserStats.toString());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BrowserStats{");
        sb.append("browserStats=").append(browserStats);
        sb.append('}');
        return sb.toString();
    }
}

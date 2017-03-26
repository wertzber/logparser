package com.logparser.elad.parser;

//import net.sf.uadetector.*;
//import net.sf.uadetector.service.UADetectorServiceFactory;


import com.logparser.elad.flow.FlowManager;
import com.logparser.elad.model.Summary;
import com.logparser.elad.model.UAParserFields;
import com.logparser.elad.model.UAParserFieldsBuilder;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by eladw on 3/25/17.
 */
public class UAParser implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(UAParser.class);

    private UserAgentStringParser parser;
    private List<String> lines;
    private Summary summary;

    public UAParser (UserAgentStringParser parser, List<String> lines, Summary summary){
        this.parser = parser;
        this .lines = lines;
        this .summary = summary;
    }


    public void run(){

        lines.stream().map(line -> {
                ReadableUserAgent lineP  = parser.parse(line);
                return lineP;
        })
        .map(parser -> createUaParser(parser))
        .forEach(uaParserFields -> {
            logger.debug("lines: {} uaFields: {}", lines, uaParserFields);
            summary.getOsStats().calcStatistics(uaParserFields);
            summary.getBrowserStats().calcBrowserStatistics(uaParserFields);
            logger.debug("Total handle: " + summary.addNumOfHandleRows());

        });
        if(summary.getNumOfReadRows()-summary.getNumOfHandleRows() < 10) {
            logger.info("## Done ###");
            FlowManager.latch.countDown();
        }
        logger.info("handles {} lines from total read: {}", summary.getNumOfHandleRows(), summary.getNumOfReadRows());

    }

    private UAParserFields createUaParser(ReadableUserAgent parse){
        return new UAParserFieldsBuilder()   //TODO: add validation and error handle
                .withBrowser(parse.getFamily().getName())
                .withBrowserVersion(parse.getVersionNumber().toVersionString())
                .withOsName(parse.getOperatingSystem().getFamily().getName())
                .withOsVersion(parse.getOperatingSystem().getVersionNumber().toVersionString())
                .build();
    }

}

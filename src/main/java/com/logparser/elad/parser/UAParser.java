package com.logparser.elad.parser;

//import net.sf.uadetector.*;
//import net.sf.uadetector.service.UADetectorServiceFactory;


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

        lines.stream().map(line -> parser.parse(line))
            .map(parser -> createUaParser(parser))
            .forEach(uaParserFields-> {
                logger.debug("lines: {} uaFields: {}", lines, uaParserFields);
                summary.getOsStats().calOsStatistics(uaParserFields);
                summary.getBrowserStats().calBrowserStatistics(uaParserFields);
            });
    }

    private UAParserFields createUaParser(ReadableUserAgent parse){
        return new UAParserFieldsBuilder()   //TODO: add validation and error handle
                .withBrowser(parse.getFamily().getName())
                .withBrowserVersion(parse.getVersionNumber().toVersionString())
                .withOsName(parse.getOperatingSystem().getName())
                .withOsVersion(parse.getOperatingSystem().getVersionNumber().toVersionString())
                .build();
    }

}

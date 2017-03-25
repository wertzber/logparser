package com.logparser.elad.parser;

//import net.sf.uadetector.*;
//import net.sf.uadetector.service.UADetectorServiceFactory;


import com.logparser.elad.model.UAParserFields;
import com.logparser.elad.model.UAParserFieldsBuilder;
import net.sf.uadetector.OperatingSystem;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by eladw on 3/25/17.
 */
public class UAParser {

    private static final Logger logger = LoggerFactory.getLogger(UAParser.class);

    private static UserAgentStringParser parser;

    public UAParser (){
        parser = UADetectorServiceFactory.getResourceModuleParser();

    }


    public UAParserFields prepareUAFields(String line){

        ReadableUserAgent parse = parser.parse(line);
        UAParserFields uaParserFields = new UAParserFieldsBuilder()   //TODO: add validation and error handle
                .withBrowser(parse.getFamily().getName())
                .withBrowserVersion(parse.getVersionNumber().toVersionString())
                .withOsName(parse.getOperatingSystem().getName())
                .withOsVersion(parse.getOperatingSystem().getVersionNumber().toVersionString())
                .build();
        logger.debug("line: {} uaFields: {}", line, uaParserFields);
        return uaParserFields;
    }

}

package com.logparser.elad.parser;

import com.logparser.elad.model.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eladw on 3/26/17.
 */
public class IPParser implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(IPParser.class);

    public static final String IPADDRESS_PATTERN =
            "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    private Pattern compile = Pattern.compile(IPADDRESS_PATTERN);
    private Summary summary;
    private List<String>lines;

    public IPParser(List<String> lines,Summary summary) {
        compile = Pattern.compile(IPADDRESS_PATTERN);
        this.lines = lines;
        this.summary = summary;
    }


    public String getIPV4(String line){
        Matcher matcher = compile.matcher(line);
        if (matcher.find()) {
            return matcher.group();
        } else{
            return null;
        }
    }

    @Override
    public void run() {
        lines.stream().map(line -> getIPV4(line))
                .filter(ipResult-> ipResult!=null)
                .forEach(ip -> {
                    logger.debug("adding ip {}", ip);
                    summary.getIpStats().calcIPStatistics(ip);
                });
    }
}

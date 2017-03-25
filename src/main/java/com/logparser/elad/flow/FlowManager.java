package com.logparser.elad.flow;

import com.logparser.elad.file.FileReader;
import com.logparser.elad.model.Summary;
import com.logparser.elad.model.UAParserFields;
import com.logparser.elad.parser.UAParser;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by eladw on 3/25/17.
 * Manage the flow with all actions needed.
 */
public class FlowManager {

    private static final Logger logger = LoggerFactory.getLogger(FlowManager.class);
    public static final int PRINT_PROGRESS = 300;
    public static final int UA_PARSERS_THREADS = 10;
    public static final int INITIAL_LINES_CAPACITY = 500;

    private FileReader fileReader;
    private Summary summary;
    ExecutorService uaExecutor = Executors.newFixedThreadPool(UA_PARSERS_THREADS); //start new thread pool
    private UserAgentStringParser parser;



    public FlowManager(){
        fileReader = new FileReader("/Users/eladw/git/logparser/src/main/resources/all");
        summary = new Summary();
        parser = UADetectorServiceFactory.getResourceModuleParser();
    }

    public void startFlow() {

        boolean stop = false;
        List<String> bulkOfLines = new ArrayList<>(INITIAL_LINES_CAPACITY);
        while(!stop){ //todo - fix and make it more elegant
            Optional<String> line = fileReader.getNextLine();
            if(summary.getNumOfReadRows() % PRINT_PROGRESS == 0){
                logger.info("So far read {} lines", summary.getNumOfReadRows());
            }
            if (!checkLineValidOutput(line)) {
                stop=true;
            } else {
                bulkOfLines.add(line.get());
                if(bulkOfLines.size() % INITIAL_LINES_CAPACITY == 0){
                    uaExecutor.execute(new UAParser(parser, bulkOfLines, summary));
                }
            }
        }
        //for last lines
        uaExecutor.execute(new UAParser(parser, bulkOfLines, summary));






    }

    private boolean checkLineValidOutput(Optional<String> line) {
        summary.enlargeNumOfReadRows();

        if(line.isPresent() && line.get().contains(FileReader.ERROR_READ)){
            logger.error("File reader failed, stopping the search after {} lines", summary.getNumOfReadRows());
            return false;
        } else if(!line.isPresent()){
            logger.info("Finish reading file {} after {} lines, about to close it", fileReader.getFilename(),summary.getNumOfReadRows());
            fileReader.closeFile();
            return false;
        }
        return true;
    }
}

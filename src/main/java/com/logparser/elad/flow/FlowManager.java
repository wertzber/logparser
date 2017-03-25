package com.logparser.elad.flow;

import com.logparser.elad.file.FileReader;
import com.logparser.elad.model.Summary;
import com.logparser.elad.parser.UAParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Created by eladw on 3/25/17.
 * Manage the flow with all actions needed.
 */
public class FlowManager {

    private static final Logger logger = LoggerFactory.getLogger(FlowManager.class);

    private FileReader fileReader;
    private Summary summary;
    private UAParser uaParser;

    public FlowManager(){
        fileReader = new FileReader("/Users/eladw/git/logparser/src/main/resources/all");
        summary = new Summary();
        uaParser = new UAParser();
    }

    public void startFlow() {
        while(true){ //todo - fix and make it more elegant
            Optional<String> line = fileReader.getNextLine();
            logger.info("So far read {} lines", summary.getNumOfReadRows());
            if (!checkLineValidOutput(line)) return;
            uaParser.prepareUAFields(line.get());


        }


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

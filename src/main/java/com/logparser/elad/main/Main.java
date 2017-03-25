package com.logparser.elad.main;

import com.logparser.elad.flow.FlowManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by eladw on 3/25/17.
 */
public class Main {


    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("logparser started");
        FlowManager flowManager = new FlowManager();
        flowManager.startFlow();
    }
}

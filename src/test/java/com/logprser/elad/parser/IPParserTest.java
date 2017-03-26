package com.logprser.elad.parser;

import com.logparser.elad.parser.IPParser;
import junit.framework.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by eladw on 3/26/17.
 */
public class IPParserTest {

    private static final Logger logger = LoggerFactory.getLogger(IPParserTest.class);

    @Test
    public void testIPParser(){
        IPParser ipParser = new IPParser(null, null);
        String result = ipParser.getIPV4("dsf1.1.2.3");
        Assert.assertEquals(result, "1.1.2.3");

    }
    @Test
    public void testIPParserReal(){
        String line = "180.76.5.159 - - [20/Jan/2013:12:36:34 -0600] \"GET / HTTP/1.1\" 200 10087 \"-\" \"Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)\" 214 10350 - 139456\n";
        IPParser ipParser = new IPParser(null,null);
        String result = ipParser.getIPV4(line);
        Assert.assertEquals(result, "180.76.5.159");

    }




}

package com.logprser.elad;

import com.logparser.elad.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

/**
 * Created by eladw on 3/26/17.
 */
public class TestMap {

    private static final Logger logger = LoggerFactory.getLogger(TestMap.class);


    public static void main(String[] args) {
        Map<String,LongAdder> m = new ConcurrentHashMap<>();
        LongAdder l1 = new LongAdder();
        l1.increment();
        l1.increment();
        l1.increment();

        LongAdder l2 = new LongAdder();
        l2.increment();
        l2.increment();
        l2.increment();
        l2.increment();
        l2.increment();
        l2.increment();


        LongAdder l3 = new LongAdder();
        l3.increment();
        l3.increment();



        m.put("elad", l1);
        m.put("elad1", l2);
        m.put("elad3", l3);


        List<Result> results = new ArrayList<>();

        Comparator<Result> byCount =
                (Result o1, Result o2)-> o1.getCount() - (o2.getCount());

        Set<Map.Entry<String, LongAdder>> set = m.entrySet();
        for(Map.Entry<String, LongAdder> entry : set){
            Result r = new Result(entry.getKey(),entry.getValue().intValue());
            results.add(r);
        }
        results.stream().sorted(byCount).forEach(n->logger.info(n.toString()));


//        Read more: http://www.java67.com/2015/01/how-to-convert-map-to-list-in-java.html#ixzz4cQ6yRwYM
//        Comparator<Map<String,LongAdder>> byVal =
//                (Map<String,LongAdder> o1, Map<String,LongAdder> o2)->o1.entrySet().intValue().compareTo(o2.intValue());
//
//        m.entrySet().stream().sorted(byVal).collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue())).forEach((k,v)->logger.info(v.toString()));


    }
}

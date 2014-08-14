package com.shawn.synchronization;

import java.util.concurrent.Callable;

/**
 * Created by shawn on 8/5/14.
 */
public class CallableRecord implements Callable<Record> {
    public Record call() {
        Record record = new IntRecord(0);
        for (long i = 0; i < 10000; i++)
            record.increment();
        return record;
    }
}

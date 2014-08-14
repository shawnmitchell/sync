package com.shawn.synchronization;

/**
 * Created by shawn on 7/28/14.
 */

public class WorkerThread implements Runnable {

    private Record record;
    private int iterations;
    static private Object lock = new Object();


    public WorkerThread(Record record, int iterations) {
        this.record = record;
        this.iterations = iterations;
    }

    public void run() {
        for (long ct = 0; ct < iterations; ct++)
            record.increment();
    }
}

package com.shawn.synchronization;

/**
 * Created by shawn on 7/29/14.
 */
public class SynchronizedRecord implements Record {
    private int record;

    public SynchronizedRecord(int record) {
        this.record = record;
    }

    public synchronized void reset() {
        record = 0;
    }

    public synchronized int getValue() {
        return record;
    }

    public synchronized void increment() {
        record++;
    }

    public synchronized void decrement() {
        record--;
    }
}

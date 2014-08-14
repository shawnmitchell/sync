package com.shawn.synchronization;

/**
 * Created by shawn on 7/29/14.
 */
public class IntRecord implements Record {
    private int record;

    public IntRecord(int record) {
        this.record = record;
    }

    public void reset() {
        record = 0;
    }

    public int getValue() {
        return record;
    }

    public void increment() {
        record++;
    }

    public void decrement() {
        record--;
    }

}

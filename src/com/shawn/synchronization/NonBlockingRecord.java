package com.shawn.synchronization;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by shawn on 7/29/14.
 */
public class NonBlockingRecord implements Record {
    private AtomicReference<Integer> record;
    private long collisions = 0L;

    public NonBlockingRecord(int record) {
        this.record = new AtomicReference<>(record);
    }

    public void reset() {
        record.set(0);
    }

    public int getValue() {
        return record.get();
    }

    public void increment() {
        Integer val;

        while(true) {
            val = record.get();
            if (record.compareAndSet(val, val+1))
                return;
            collisions++;
        }
    }

    public void decrement() {
        Integer val;

        while (true) {
            val = record.get();
            if (record.compareAndSet(val, val-1))
                return;
            collisions++;
        }
    }

    public long getCollisions() {
        return collisions;
    }
}

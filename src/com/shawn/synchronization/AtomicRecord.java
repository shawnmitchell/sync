package com.shawn.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shawn on 7/29/14.
 */
public class AtomicRecord implements Record{

    private AtomicInteger value;

    public AtomicRecord(int value) {
        this.value = new AtomicInteger(value);
    }

    public void reset() {
        value.set(0);
    }

    public int getValue() {
        return value.get();
    }

    public void increment() {
        value.incrementAndGet();
    }

    public void decrement() {
        value.decrementAndGet();
    }
}
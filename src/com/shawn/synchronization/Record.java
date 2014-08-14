package com.shawn.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shawn on 7/28/14.
 */


public interface Record {

    public void reset();

    public int getValue();

    public void increment();

    public void decrement();
}

package com.shawn.synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by shawn on 7/28/14.
 */


public class Application {

    static private int numThreads = 50;
    static private int numWorkers = 500;
    static private int numIterations = 500;
//        static private Record record = new SynchronizedRecord(0);
//        static private Record record = new AtomicRecord(0);
//        static private Record record = new IntRecord(0);
    static private Record record = new NonBlockingRecord(0);


    public static void main(String[] args) {

        availableProcessors();
        List<Future<Record>> recordFutures = new ArrayList<>(10000);
        ExecutorService pool = Executors.newFixedThreadPool(numThreads);

        long startTime = System.nanoTime();

        for (int i = 0; i < 10000; i++) {
            Callable<Record> worker = new CallableRecord();
            Future<Record> rec = pool.submit(worker);
            recordFutures.add(rec);
        }

        long total = 0;
        try {
            for (Future<Record> r : recordFutures) {
                total += r.get().getValue();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        }

        try {
            for (int i = 0; i < numWorkers; i++)
                pool.execute(new WorkerThread(record, numIterations));

            if (!pool.awaitTermination(500, TimeUnit.MILLISECONDS))
                pool.shutdown();

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
/*
        ArrayList<Thread> threads = new ArrayList<Thread>(numThreads);

        for (int i = 0; i < numThreads; i++) {
            threads.add(i, new Thread(new WorkerThread(record, numIterations)));
        }
        System.out.println("number of threads: " + threads.size());

        try {
            for (Thread thread : threads)
                thread.start();
            for (Thread thread : threads)
                thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
        long elapsedTime = System.nanoTime() - startTime;

        System.out.println("expected total: " + numIterations * numThreads + " actual total: " + record.getValue() + " time: " + elapsedTime / 1000);

        System.out.println("total: "+ total);
    }

    private static void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void availableProcessors() {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(Integer.toString(processors) + " processor"
                + (processors != 1 ? "s are " : " is ")
                + "available");

    }
}

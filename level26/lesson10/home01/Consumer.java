package com.javarush.test.level26.lesson10.home01;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Силя on 25.07.2016.
 */
public class Consumer implements Runnable
{
    protected BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            int i = 0;
            while (true) {
                Thread.sleep(500);
                System.out.println(queue.poll());
            }
        } catch (InterruptedException e) {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}

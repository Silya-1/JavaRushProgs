package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Силя on 31.07.2016.
 */
public class Cook extends Observable implements Runnable
{
    private LinkedBlockingQueue<Order> queue;
    private boolean busy;
    public final String name;
    public Cook(String name)
    {
        this.name = name;
    }
    @Override
    public String toString()
    {
        return name;
    }

    public void startCookingOrder(Order order)
    {
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        try {
            Thread.sleep(10 * order.getTotalCookingTime());
        }catch (InterruptedException ex) {}
        StatisticEventManager.getInstance().register(
                new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime()*60, order.getDishes()));
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    public boolean isBusy()
    {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                startCookingOrder(queue.take());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

}

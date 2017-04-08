package com.javarush.test.level27.lesson15.big01;


import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Силя on 30.07.2016.
 */
public class Restaurant
{
    private static final LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();;
    private static final int ORDER_CREATING_INTERVAL = 100;
    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);
        Waitor waitor = new Waitor();
        Cook c1 = new Cook("Ivanov");
        c1.setQueue(queue);
        c1.addObserver(waitor);
        Cook c2 = new Cook("Petrov");
        c2.setQueue(queue);
        c2.addObserver(waitor);
        Thread cook1thread = new Thread(c1);
        Thread cook2thread = new Thread(c2);
        cook1thread.start();
        cook2thread.start();
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            Tablet tablet = new Tablet(i+1);
            tablet.setQueue(queue);
            tablets.add(tablet);
        }
        Thread t = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        t.start();
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {}
        t.interrupt();
        cook1thread.interrupt();
        cook2thread.interrupt();
        try
        {
            cook1thread.join();
            cook2thread.join();
        }
        catch (InterruptedException e) {}
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}

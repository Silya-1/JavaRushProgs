package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by Силя on 30.07.2016.
 */
public class Order
{
    protected List<Dish> dishes;
    private Tablet tablet;

    public Order(Tablet tablet) throws IOException
    {
        this.tablet = tablet;
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        initDishes();
    }

    @Override
    public String toString() {
        if (dishes == null || dishes.isEmpty()) {
            return "";
        } else {
            return "Your order: " + dishes.toString() + " of " + tablet;
        }
    }


    public int getTotalCookingTime()
    {
        int totalDuration = 0;
        for (Dish dish : dishes)
        {
            totalDuration += dish.getDuration();
        }
        return totalDuration;
    }

    protected void initDishes() throws IOException
    {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public boolean isEmpty() {
        return dishes == null || dishes.isEmpty();
    }

    public List<Dish> getDishes()
    {
        return dishes;
    }

    public Tablet getTablet()
    {
        return tablet;
    }
}

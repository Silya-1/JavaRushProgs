package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Силя on 02.08.2016.
 */
public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException
    {
        super(tablet);
    }
    @Override
    protected void initDishes() throws IOException
    {
        List<Dish> dishes = new ArrayList<>();
        Collections.addAll(dishes, Dish.values());
        Collections.shuffle(dishes); // делает рендом
        this.dishes = dishes;
    }
}
package com.javarush.test.level15.lesson12.bonus02;

/**
 * Created by Силя on 25.06.2016.
 */
public abstract class DrinkMaker
{
    public abstract  void getRightCup();
    public abstract  void putIngredient();
    public abstract  void pour();
    public void makeDrink()
    {
        this.getRightCup();
        this.putIngredient();
        this.pour();
    }
}

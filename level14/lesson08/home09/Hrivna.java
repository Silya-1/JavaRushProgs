package com.javarush.test.level14.lesson08.home09;

/**
 * Created by Силя on 24.06.2016.
 */
class Hrivna extends Money
{
    public Hrivna(double amount)
    {
        super(amount);
    }

    public String getCurrencyName (){return "HRN";}
}

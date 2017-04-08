package com.javarush.test.level14.lesson08.home09;

public abstract class Money
{
    private double Amount;
    public Money(double amount)
    {
        this.Amount = amount;
    }
    public double getAmount(){ return Amount; };
    public abstract String getCurrencyName();
}


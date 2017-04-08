package com.javarush.test.level14.lesson06.home01;

/**
 * Created by Силя on 23.06.2016.
 */
public class RussianHen extends Hen
{
    public int getCountOfEggsPerMonth() {
        return 600;
    }
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.RUSSIA + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}

package com.javarush.test.level14.lesson06.home01;

/**
 * Created by Силя on 23.06.2016.
 */
public class UkrainianHen extends Hen
{
    public int getCountOfEggsPerMonth(){return 6;};
    public String getDescription(){return super.getDescription() + " Моя страна - " + Country.UKRAINE +
            ". Я несу " + this.getCountOfEggsPerMonth()+ " яиц в месяц.";}
}

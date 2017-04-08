package com.javarush.test.level14.lesson06.home01;

/**
 * Created by Силя on 23.06.2016.
 */
public class MoldovanHen extends  Hen
{
    public int getCountOfEggsPerMonth(){return 64;};
    public String getDescription(){return super.getDescription() + " Моя страна - " + Country.MOLDOVA +
            ". Я несу " + this.getCountOfEggsPerMonth()+ " яиц в месяц.";}
}

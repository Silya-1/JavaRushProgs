package com.javarush.test.level29.lesson15.big01.human;

/**
 * Created by silya on 08.08.2016.
 */
public class Soldier extends Human
{
    public Soldier( String name,int age) {
        super(name,age);
    }
    public void live(){
        this.fight();
    }

    public void fight(){
    }
}

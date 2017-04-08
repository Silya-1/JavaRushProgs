package com.javarush.test.level14.lesson08.bonus03;

/**
 * Created by Силя on 24.06.2016.
 */
public class Singleton
{
    public static Singleton getInstance(){return sr;};
    private  static Singleton sr = new Singleton();
    private Singleton(){};
}

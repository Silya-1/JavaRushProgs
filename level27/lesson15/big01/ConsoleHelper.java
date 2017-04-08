package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Силя on 30.07.2016.
 */
public class ConsoleHelper
{
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        if(!message.isEmpty())System.out.println(message);
    }

    public static String readString() throws IOException
    {
        String str = reader.readLine();
        return str;

    }

    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> dishes = new ArrayList<>();
        String str;
        writeMessage("Choose dish...(" + Dish.allDishesToString() + ")");
        while (true)
        {
            str = readString();
            if ("exit".equals(str))
            {
                break;
            }
            try
            {
                dishes.add(Dish.valueOf(str));
            }catch (IllegalArgumentException q)
            {
                ConsoleHelper.writeMessage(String.format("%s is not detected", str));
            }

        }
        return dishes;
    }
}

package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String URL;
        try
        {
            URL = reader.readLine();
            int i = URL.indexOf("?"); //find ?
            URL = URL.substring(i + 1); //substrac all before space
            int tmp;
            ArrayList<String> list = new ArrayList<>();
            for (String d : URL.split("&"))//Разделяем строки по &
            {
                list.add(d);
            }
            for (String d : list)
            {
                if (d.contains ("="))
                {
                    System.out.print(d.substring(0, d.indexOf("=")) + " ");

                } else
                {
                    System.out.print(d + " ");
                }
            }
            System.out.println();
            for (String d : list)
            {
                if (d.startsWith("obj="))
                {
                    String s = d.substring(d.indexOf("=") + 1);
                    try
                    {
                        alert(Double.parseDouble(s));
                    }
                    catch (Exception e){
                        alert(s);
                    }
                }
            }
        }
        catch (IOException a)
        {
            throw a;
        }
    }
    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}

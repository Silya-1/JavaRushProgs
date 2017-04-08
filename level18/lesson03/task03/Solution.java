package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        int max = Integer.MIN_VALUE;
        ArrayList<Integer> list_v = new ArrayList<>();
        ArrayList<Integer> list_n = new ArrayList<>();
        while (inputStream.available() > 0)
        {
            int s = inputStream.read();
            if(list_v.contains(s))
            {
               int al = list_n.get(list_v.indexOf(s));
                al++;
               // System.out.println(al + "  " + s);
                list_n.set(list_v.indexOf(s), al);
                if(al > max)
                    max = al;
            }else
            {
                list_n.add(0, 1);
                list_v.add(0, s);
            }
        }
        for(int num : list_n)
        if(num == max)
            System.out.print(list_v.get(list_n.indexOf(num)) + " ");
        inputStream.close();
        reader.close();
    }
}


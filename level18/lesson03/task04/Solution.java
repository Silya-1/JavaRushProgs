package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        Map<Integer,Integer> list = new HashMap<Integer,Integer>();
        while (inputStream.available()>0){
            int key = inputStream.read();
            if(list.containsKey(key)){
                int value = list.get(key);
                list.put(key, ++value);
            }
            else list.put(key, 1);
        }
        int min = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Integer> l : list.entrySet())
        {
           if(l.getValue() < min)
                min = l.getValue();
        }
        for(Map.Entry<Integer, Integer> l : list.entrySet())
        {
            if(l.getValue() == min)
                System.out.println(l.getKey() + " ");
        }
        inputStream.close();
        reader.close();
    }
}

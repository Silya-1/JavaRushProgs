package com.javarush.test.level22.lesson09.task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws IOException
    {
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader filereader = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        StringBuilder stringBuilder = new StringBuilder(filereader.readLine());
        while (filereader.ready())
        {
            stringBuilder.append(" " + filereader.readLine());
        }
        filereader.close();
        String[] array = stringBuilder.toString().split(" ");
        StringBuilder result = getLine(array);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words)
    {
        if(words == null)
            return new StringBuilder();
        ArrayList<String> array = new ArrayList<>();
        Collections.addAll(array, words);
        StringBuilder res = null;
        if (array.size() == 0)
            return new StringBuilder();
            int count = 0;
            while (count != array.size())
            {
                Collections.shuffle(array);
                res = new StringBuilder(array.get(0));
                count = 1;
                for (int i = 1; i < array.size(); i++)
                {
                    if(array.get(i).isEmpty())
                        continue;
                    String a = array.get(i).toLowerCase();
                    String b = res.toString().toLowerCase();
                    if (a.charAt(0) == b.charAt(res.length() - 1))
                    {
                        res.append(" ").append(array.get(i));
                        count++;
                        continue;
                    }
                }
            }
            return res;
    }
}


package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader filereader = new BufferedReader(new FileReader(reader.readLine()));
        StringBuilder str = new StringBuilder(filereader.readLine());
        while (filereader.ready())
        {
            str.append(" " + filereader.readLine());
        }
        String res = str.toString();
        String array[] = res.split(" ");
        for (int i = 0; i < array.length; i++)
        {
            String word = array[i];
            for (int j = i + 1; j < array.length; j++)
            {
                StringBuilder sb2 = new StringBuilder(word);
                sb2 = sb2.reverse();
                String s = sb2.toString();
                if (s.equals(array[j]) && (!s.isEmpty()))
                {
                    Pair pair = new Pair();
                    pair.first = word;
                    pair.second = s;
                    result.add(pair);
                    array[i] = "";
                    array[j] = "";
                }
            }
        }
        for (int i = 0; i < result.size(); i++)
        {
            System.out.println(result.get(i).toString());
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}

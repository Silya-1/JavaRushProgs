package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(reader.readLine());
        reader.close();
        String inf = "";
        while (fileReader.ready())
        {
            inf += "" + (char)fileReader.read();
        }
        fileReader.close();
        //System.out.println(inf);
        String[] array = inf.split("\\W");
        int count = 0;
        for (String str : array)
        {
            if(str.equals("world"))
                count++;
        }
        System.out.println(count);

    }
}

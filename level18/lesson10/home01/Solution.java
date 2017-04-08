package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        FileInputStream fileInStream = new FileInputStream(args[0]);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int count = 0;
        while (fileInStream.available() > 0)
        {
            int c = fileInStream.read();
            if(alphabet.contains((char)c +""))
                count++;
        }
        fileInStream.close();
        System.out.println(count);
    }
}

package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(reader.readLine());
        FileWriter fileWriter = new FileWriter(reader.readLine());
        reader.close();
        String inf = "";
        while (fileReader.ready())
        {
            inf += "" + (char)fileReader.read();
        }
        fileReader.close();
        String[] array = inf.split(" ");
        for (String str : array)
            try
            {
                int res = Integer.parseInt(str);
                fileWriter.write("" + res + " ");
            }catch (Exception d){}
        fileWriter.close();
    }
}

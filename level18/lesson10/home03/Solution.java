package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
        String name1 = reader.readLine();
        String name2 = reader.readLine();
        String name3 = reader.readLine();
        reader.close();
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(name1);
            FileInputStream fileInputStream1 = new FileInputStream(name2);
            FileInputStream fileInputStream2 = new FileInputStream(name3);
            byte[] array = new byte[fileInputStream1.available()];
            fileInputStream1.read(array);
            fileOutputStream.write(array);
            array = null;
            array = new byte[fileInputStream2.available()];
            fileInputStream2.read(array);
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.write(array);
            fileInputStream1.close();
            fileInputStream2.close();
            fileOutputStream.close();
        }catch (Exception a){a.printStackTrace();}
    }
}

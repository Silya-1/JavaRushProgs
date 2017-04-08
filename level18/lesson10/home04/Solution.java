package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String first = reader.readLine();
            String second =  reader.readLine();
            reader.close();
            FileInputStream in_read = new FileInputStream(first);
            FileInputStream from_read = new FileInputStream(second);
            byte[] array = new  byte[in_read.available()];
            if (in_read.available() > 0)
                in_read.read(array);
            in_read.close();
            FileOutputStream in_write = new FileOutputStream(first);
            in_write.write("\n".getBytes());
            byte[] buf = new byte[from_read.available()];
            from_read.read(buf);
            in_write.write(buf);
            in_write.write(array);
            from_read.close();
            in_write.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

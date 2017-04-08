package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream = new FileInputStream(reader.readLine());
        FileOutputStream fileOutputStream = new FileOutputStream(reader.readLine());
        reader.close();
        int count = 0;
        while (fileInputStream.available() > 0)
        {
            int c = fileInputStream.read();
            count++;
            if((count % 2) == 0)
                fileOutputStream.write(c);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}

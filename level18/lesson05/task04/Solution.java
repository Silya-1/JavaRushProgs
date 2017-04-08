package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        FileOutputStream out1 = new FileOutputStream(reader.readLine());
        if(inputStream.available() > 0)
        {
            int count = inputStream.available();
            byte[] buffer = new byte[count];
            inputStream.read(buffer);
            byte[] buffer_sw = new  byte[count];
            int i = buffer.length - 1;
            for(byte sw : buffer)
            {
                buffer_sw[i] = sw;
                i--;
            }
            out1.write(buffer_sw);
        }
        reader.close();
        inputStream.close();
        out1.close();
    }
}

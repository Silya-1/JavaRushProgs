package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        FileOutputStream out1 = new FileOutputStream(reader.readLine());
        FileOutputStream out2 = new FileOutputStream(reader.readLine());
        if(inputStream.available() > 0)
        {
            int count = inputStream.available();
            if (count % 2 == 0)
                count /= 2;
            else
                count = count / 2 + 1;
            byte[] buffer = new byte[count];
            inputStream.read(buffer);
            out1.write(buffer);
            buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            out2.write(buffer);
        }
        out1.close();
        out2.close();
        inputStream.close();
        reader.close();
    }
}

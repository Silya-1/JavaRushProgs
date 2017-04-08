package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            Scanner scanner = new Scanner(new FileReader(reader.readLine()));
            FileOutputStream outputStream = new FileOutputStream(reader.readLine());
            String new_file = "";
            while (scanner.hasNext())
            {
                double data = scanner.nextDouble();
                int res = (int) Math.round(data);
                new_file += res + " ";
            }
            outputStream.write(new_file.getBytes());
            scanner.close();
            outputStream.close();
            reader.close();
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

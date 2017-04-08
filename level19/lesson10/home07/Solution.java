package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader in1 = new BufferedReader(new FileReader(args[0]));
        BufferedWriter out1 = new BufferedWriter(new FileWriter(args[1]));
        String res = "";
        while (in1.ready())
        {
            String test = in1.readLine();
            res += test + " ";
        }
        String[] arr = res.split(" ");
        int num = 0;
        for(int i = 0; i < arr.length; i++)
            if (arr[i].length() > 6)
                num = i;

        for (int s = 0; s <= num; s++)
        {
            if (arr[s].length() > 6 && !arr[s].isEmpty())
            {
                 out1.write(arr[s]);
                if(s != num )
                    out1.write(",");
            }
        }
            in1.close();
            out1.close();
    }
}


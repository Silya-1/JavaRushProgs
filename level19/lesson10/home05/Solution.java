package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader in1 = new BufferedReader(new FileReader(args[0]));
        BufferedWriter out1 = new BufferedWriter(new FileWriter(args[1]));
        while (in1.ready())
        {
            String s = in1.readLine();
            for(String str : s.split(" "))
            {
                Pattern pattern = Pattern.compile("[0-9]");
                Matcher matcher = pattern.matcher(str);
                if (matcher.find())
                {
                    out1.write(str + " ");

                }
            }
        }
        out1.close();
        in1.close();
    }
}

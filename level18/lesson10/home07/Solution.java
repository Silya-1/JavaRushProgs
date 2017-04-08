package com.javarush.test.level18.lesson10.home07;

/* Поиск данных внутри файла
Считать с консоли имя файла
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int)
Закрыть потоки. Не использовать try-with-resources

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity

где id - int
productName - название товара, может содержать пробелы, String
price - цена, double
quantity - количество, int

Информация по каждому товару хранится в отдельной строке
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        String s = args[0];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            BufferedReader filereader = new BufferedReader(new FileReader(reader.readLine()));
            while (filereader.ready())
            {
                String line = filereader.readLine();
                String id  = line.substring(0, line.indexOf(" "));
                id = id.trim();
                if(id.equals(s))
                {
                    System.out.println(line);
                }

            }
            filereader.close();
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

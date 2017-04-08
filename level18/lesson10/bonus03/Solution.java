package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        if (args[0].equals("-u"))
        {
            try
            {
                BufferedReader filereader = new BufferedReader(new FileReader(filename));
                String all = "";
                String idToAdd = String.format("%-8.8s", args[1]);
                while (filereader.ready())
                {
                    String line = filereader.readLine();
                    String id = line.substring(0, 8);
                    if(idToAdd.equals(id))
                    {
                        String price = String.format("%-8.8s", args[args.length - 2]);
                        String quantity = String.format("%-4.4s", args[args.length -1]);
                        String productName = "";
                        for(int i = 2; i < args.length - 2; i++)
                            productName += args[i];
                        productName = String.format("%-30.30s", productName);
                        all += idToAdd + productName + price + quantity + "\n";
                    }else {
                        all += (line + "\n");}
                }
                filereader.close();
                FileWriter fileWriter = new FileWriter(filename);
                fileWriter.write(all);
                fileWriter.close();
            }
            catch (Exception a)
            {
                a.printStackTrace();
            }
        }
        if(args[0].equals("-d"))
        {
            FileReader fr = new FileReader(filename);
            BufferedReader fileReader = new BufferedReader(fr);
            String idToDelete = String.format("%-8.8s", args[1]);
            String allText = "";
            while (fileReader.ready()) {
                String line = fileReader.readLine();
                String id = line.substring(0, 8);
                if (!id.equals(idToDelete)) {
                    allText += (line+"\n");
                }
            }
            fileReader.close();
            fr.close();
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(allText);
            fileWriter.close();
        }
    }
}

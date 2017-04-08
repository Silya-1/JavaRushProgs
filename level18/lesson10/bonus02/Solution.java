package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        if(args[0].equals("-c"))
        {
            String productName = "";
            for(int i = 1; i < args.length - 2; i++)
                productName += args[i];
            if(productName.length() > 30)
                productName = productName.substring(0, 30);
            String price = args[args.length - 2];
            if (price.length() > 8)
                 price = price.substring(0,8);
            String quantity = args[args.length - 1];
            if(quantity.length() > 4)
                quantity = quantity.substring(0,4);
            try
            {
                BufferedReader filereader = new BufferedReader(new FileReader(filename));
                FileWriter fileWriter = new FileWriter(filename, true);
                int id = 0;
                while (filereader.ready())
                {
                    String line = filereader.readLine();
                    if(! line.isEmpty())
                    {
                        if(line.length() > 8)
                            line = line.substring(0,8).trim();
                        int idmax = Integer.parseInt(line);
                        if(id < idmax)
                            id = idmax;
                    }
                }
                id++;
                String newPr = "" + id;
                while (newPr.length() < 8)
                    newPr += " ";
                while (productName.length() < 30)
                    productName += " ";
                while (price.length() < 8)
                    price += " ";
                while (quantity.length() < 4)
                    quantity += " ";
               fileWriter.write("\r\n" + newPr + productName + price + quantity);
                filereader.close();
                fileWriter.close();
            }catch (Exception a){a.printStackTrace();}
        }
    }
}

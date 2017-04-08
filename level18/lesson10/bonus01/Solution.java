package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        if(args[0].equals("-e"))
            start_sh(args[1], args[2]);
        else if (args[0].equals("-d"))
            start_dsh(args[1], args[2]);

    }
    public static void start_sh(String from, String to) throws IOException
    {
        FileInputStream in = new FileInputStream(from);
        FileOutputStream out = new FileOutputStream(to);
        while (in.available() > 0)
        {
            int s = in.read();
            s++;
            out.write(s);
        }
    }

    public static void start_dsh(String from, String to) throws IOException
    {
        FileInputStream in = new FileInputStream(from);
        FileOutputStream out = new FileOutputStream(to);
        while (in.available() > 0)
        {
            int s = in.read();
            s--;
            out.write(s);
        }
    }

}

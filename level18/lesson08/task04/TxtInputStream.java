package com.javarush.test.level18.lesson08.task04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* UnsupportedFileName
Измените класс TxtInputStream так, чтобы он работал только с txt-файлами (*.txt)
Например, first.txt или name.1.part3.txt
Если передан не txt-файл, например, file.txt.exe, то конструктор должен выбрасывать исключение UnsupportedFileNameException
*/

public class TxtInputStream extends FileInputStream {
    public TxtInputStream(String fileName) throws UnsupportedFileNameException, FileNotFoundException
    {
        super(fileName);
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, fileName.split("."));
        if(! list.get(list.size() - 1).equals("txt"))
            throw new UnsupportedFileNameException();
    }
    public  static void main(String[] atgs)
    {
        try
        {
            TxtInputStream st = new TxtInputStream("f.txt");
        }
        catch (UnsupportedFileNameException e)
        {
            System.out.println("DSDSD");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        System.out.println("SDSDS");
    }
}


package com.javarush.test.level19.lesson03.task02;

/* Адаптер
Используйте класс AdapterFileOutputStream, чтобы адаптировать FileOutputStream к новому интерфейсу AmigoStringWriter
*/

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AdapterFileOutputStream implements AmigoStringWriter
{
    private FileOutputStream out;

    public AdapterFileOutputStream(FileOutputStream out)
    {
        this.out = out;
    }

    public void flush() throws IOException
    {out.flush();}

    public void writeString(String s) throws IOException{out.write(s.getBytes());};

    public void close() throws IOException{out.close();};
}


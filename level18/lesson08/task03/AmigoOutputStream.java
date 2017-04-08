package com.javarush.test.level18.lesson08.task03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* AmigoOutputStream
1 Измените класс AmigoOutputStream так, чтобы он стал Wrapper-ом для класса FileOutputStream. Используйте наследование.
2 При вызове метода close() должны выполняться следующая последовательность действий:
2.1 вызвать метод flush()
2.2 дописать следующий текст [JavaRush © 2012-2013 All rights reserved.], используйте метод getBytes()
2.3 закрыть поток методом close()
*/

public class AmigoOutputStream extends FileOutputStream {
    public static String fileName = "C:/tmp/result.txt";

    private FileOutputStream out;

    public AmigoOutputStream(FileOutputStream out) throws FileNotFoundException
    {
        super(fileName);
        this.out = out;
    }

    public  void write(int b) throws IOException
    {
        out.write(b);
    }

    public void flush() throws IOException
    {
        out.flush();
    }

    public  void write(byte[] b) throws IOException
    {
        out.write(b);
    }

    public void close() throws IOException
    {
        out.flush();
        String s = "JavaRush © 2012-2013 All rights reserved.";
        out.write(s.getBytes());
        out.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

}

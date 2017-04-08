package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.Comparator;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                int k = Integer.parseInt(o1.substring((o1.indexOf(".part") + 5) , o1.length()).trim());
                int m = Integer.parseInt(o2.substring((o2.indexOf(".part") + 5) , o2.length()).trim());
                if (k > m)return 1;
                    else if (k < m) return -1;
                        else return 0;
            }
        });
        String newFile = name.substring(0, name.indexOf(".part"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File(newFile));
        while (!name.equals("end"))
        {
            treeSet.add(name);
            name = reader.readLine();
        }
        reader.close();
        for (String lt : treeSet)
        {
            FileInputStream fileInputStream = new FileInputStream(lt);
            byte[] array = new byte[fileInputStream.available()];
            while (fileInputStream.available() > 0)
            {
                fileInputStream.read(array);
                fileOutputStream.write(array);
            }
            fileInputStream.close();
        }
        fileOutputStream.close();
    }
}

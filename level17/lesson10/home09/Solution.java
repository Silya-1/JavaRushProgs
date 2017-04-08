package com.javarush.test.level17.lesson10.home09;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines
4. Если список allLines НЕ содержит каких-либо строк, которые есть в forRemoveLines, то
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String name1 = reader.readLine();
             String name2 = reader.readLine();
            Scanner in1 = new Scanner(new File(name1));
            Scanner in2 = new Scanner(new File(name2));
            while (in1.hasNext())
                allLines.add(in1.nextLine());
            while (in2.hasNext())
            {
                forRemoveLines.add(in2.nextLine());
            }
        }catch (IOException a){a.printStackTrace();}
        try
        {
            new  Solution().joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }
    }

    public  void joinData() throws CorruptedDataException {
        for(int i = 0; i < forRemoveLines.size(); i++)
        {
            if(!allLines.contains(forRemoveLines.get(i)))
            {
                allLines.clear();
                throw new CorruptedDataException();
            }
        }
        if(allLines.containsAll(forRemoveLines)){
            allLines.removeAll(forRemoveLines);
            return;
        }

    }
}

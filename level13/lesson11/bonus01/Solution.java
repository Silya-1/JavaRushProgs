package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/
import java.io.*;
import java.util.*;


public class Solution
{
    public static void main(String[] args)throws IOException
    {
        // напишите тут ваш код
        ArrayList<Integer> list =  new ArrayList<Integer>();
        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
        File file = new File(s.readLine());
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext())
        {
            int data = scanner.nextInt();
            if(data % 2 == 0)
                list.add(data);
        }
        Collections.sort(list);
        for(int i = 0;i < list.size(); i++)
            System.out.println(list.get(i));
        s.close();
        scanner.close();
    }
}

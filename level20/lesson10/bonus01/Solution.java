package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;
import java.util.LinkedList;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static  void main(String[] args)
    {
        for (Integer i : getNumbers(870000001))
        {
            System.out.println(i);
        }
    }
    public static int[] getNumbers(int N)
    {
        ArrayList<Integer> list = new ArrayList<>();
        int[][] degree = new int[10][10]; //Создаём массив возведенных в степень чисел
        for (int i = 0; i <= 9; i++)
        {
            for (int j = 0; j <= 9; j++)
            {
                int chislo = 1;
                for (int n = 0; n < j; n++)
                    chislo = chislo * i;
                degree[i][j] = chislo;
            }
        }
        int[] array = new int[10];
        for (int i = 1; i <= N; i++)
        {
            int count = 0;
            int num = i;
            int res = 0;
            while (num != 0)
            {
                array[count] = num % 10;
                num = num / 10;
                count++;
            }
            for (int j = 0; j < count; j++)
            {
                res += degree[array[j]][count];
            }
            if (res == i)
                list.add(i);
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            result[i] = list.get(i);
        }
        return  result;
    }
}

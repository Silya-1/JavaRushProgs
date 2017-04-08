package com.javarush.test.level26.lesson02.task01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        final int n = array.length;
        final double mediana = n % 2 == 1
                ? array[n / 2]
                : (double)(array[n / 2 - 1] + array[n / 2]) / 2;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Collections.addAll(arrayList, array);
        Collections.sort(arrayList, new Comparator()
        {
            @Override
            public int compare(Object o1, Object o2)
            {
                double num1 = Math.abs(mediana - (Integer) o1);
                double num2 = Math.abs(mediana - (Integer) o2);
                if(num1 != num2)
                {
                    return num1 > num2 ? 1 : -1;
                }
                else
                {
                    Integer n1 = (Integer) o1;
                    Integer n2 =  (Integer) o2;
                    return n1 > n2 ? 1 : -1;
                }
            }
        });
        for(int i = 0; i < arrayList.size(); i++)
        {
            array[i] = arrayList.get(i);
        }
        return  array;
    }
    public static void main(String[] args)
    {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        array = sort(array);
        for (Integer i : array)
            System.out.println(i);
    }
}

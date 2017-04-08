package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.util.Scanner;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(NOD(a , b));
    }
    public static int NOD(int a, int b)
    {
        int res = 1;
        for(int i = 1; i <= a; i++)
        {
            if(a % i == 0 && b % i == 0)
                res = i;
            if(i > b)
                break;
        }
        return res;

    }
}

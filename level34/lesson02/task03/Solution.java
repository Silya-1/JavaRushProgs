package com.javarush.test.level34.lesson02.task03;

/* Разложение на множители с помощью рекурсии
Разложить целое число n > 1 на простые множители.
Вывести в консоль через пробел все множители в порядке возрастания.
Написать рекуррентный метод для вычисления простых множителей.
Не создавайте статические переменные и поля класса.
Пример:
132
Вывод на консоль:
2 2 3 11
*/
public class Solution {
    public void recursion(int n)
    {
        int num = 2;
        while (num <= n)
        {
            if ((n % num) == 0)
            {
                if (num != n)
                {
                    System.out.print(num + " ");
                    recursion(n / num);
                } else
                {
                    System.out.print(num);
                }
                return;
            }
            num++;
        }
    }
}

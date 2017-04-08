package com.javarush.test.level12.lesson04.task02;

/* print(int) и print(Integer)
Написать два метода: print(int) и print(Integer).
Написать такой код в методе main, чтобы вызвались они оба.
*/

public class Solution
{
    public static void main(String[] args)
    {
        int i = 0;
         print(i);
        print((Integer)i);

    }

    //Напишите тут ваши методы
    public static void print(int i) {};
    public static void print(Integer i){};
}

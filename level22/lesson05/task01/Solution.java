package com.javarush.test.level22.lesson05.task01;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException
    {
        if (string == null || string.isEmpty())
        {
            throw new TooShortStringException();
        }
        String[] res = string.split(" ");

        if(res.length < 5)
            throw new TooShortStringException();
        else return res[1] + " " + res[2] + " " + res[3] + " " + res[4];
    }

    public static void main(String[] args)
    {
        try
        {
            System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        }
        catch (TooShortStringException e)
        {
            e.printStackTrace();
        }
    }

    public static class TooShortStringException extends Throwable{
    }
}

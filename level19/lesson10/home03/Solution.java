package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException
    {
        int day, month, year;
        Calendar birthdayCalendar = new GregorianCalendar();
        String fileName = args[0];
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready())
        {
            String  s = reader.readLine();
            String[] array  = s.split(" ");
            year = Integer.parseInt(array[array.length - 1]);
            month = Integer.parseInt(array[array.length - 2]);
            day = Integer.parseInt(array[array.length - 3]);
            birthdayCalendar.set(year,month-1,day);
            String name;
            name = array[0];
            for (int i = 1; i < array.length - 3; i++)
                name += " " + array[i];
            PEOPLE.add(new Person(name, birthdayCalendar.getTime()));
        }
        reader.close();

    }

}

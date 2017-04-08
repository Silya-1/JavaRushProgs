package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException
    {
        //start here - начни тут
        //System.out.println(args.length);
        if (args.length < 2 || args.length > 6 || args.length == 3)
            return;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        if(args[0].equals("-c"))
        {
            Date date;
            date = dateFormat1.parse(args[args.length - 1]);
            String name = args[1];
            if (args[2].equals("м"))
            {
                allPeople.add(Person.createMale(name, date));
            }
            if (args[2].equals("ж"))
            {
                allPeople.add(Person.createFemale(name, date));
            }
            System.out.println(allPeople.size() - 1);
        }
        if(args[0].equals("-u"))
        {
            Person person = allPeople.get(Integer.parseInt(args[1]));
            person.setName(args[2]);
            Date date = dateFormat1.parse(args[args.length - 1]);
            person.setBirthDay(date);
            if (args[3].equals("м"))
            {
               person.setSex(Sex.MALE);
            }
            if (args[3].equals("ж"))
            {
                person.setSex(Sex.FEMALE);
            }
        }
        if(args[0].equals("-d"))
        {
            int id =Integer.parseInt(args[1]);
            allPeople.get(id).setBirthDay(null);
            allPeople.get(id).setName(null);
            allPeople.get(id).setSex(null);
        }
        if(args[0].equals("-i"))
        {
            int id =Integer.parseInt(args[1]);
            System.out.println(allPeople.get(id).getName() + " " + (allPeople.get(id).getSex().equals(Sex.MALE) ? "м" : "ж") +  " " + dateFormat.format(allPeople.get(id).getBirthDay()));
        }
       // System.out.println(allPeople.get(2).getName() + " " + (allPeople.get(2).getSex().equals(Sex.MALE) ? "м" : "ж") + " " + dateFormat.format(allPeople.get(2).getBirthDay()));
    }
}

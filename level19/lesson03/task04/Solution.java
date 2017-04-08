package com.javarush.test.level19.lesson03.task04;

import com.javarush.test.level17.lesson10.bonus01.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
    public static class PersonScannerAdapter implements  PersonScanner{

        private Scanner scanner;

        public PersonScannerAdapter(Scanner scanner)
        {
            this.scanner = scanner;
        }

        public Person read()
        {
            String s = scanner.nextLine();
            String array[] = s.split(" ");
            Calendar calendar = new GregorianCalendar(Integer.parseInt(array[5]), Integer.parseInt(array[4]) - 1, Integer.parseInt(array[3]));
            return new Person(array[1], array[2], array[0], calendar.getTime());
        }

        public void close()
        {
            scanner.close();
        }

    }
}

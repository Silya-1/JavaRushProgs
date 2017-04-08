package com.javarush.test.level18.lesson10.home09;

/* Файлы и исключения
Читайте с консоли имена файлов
Если файла не существует (передано неправильное имя файла), то
перехватить исключение FileNotFoundException, вывести в консоль переданное неправильное имя файла и завершить работу программы.
Закрыть потоки. Не использовать try-with-resources
Не используйте System.exit();
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream;
        String fileName;
        while (true)
        {
            fileName = in.readLine();
            try
            {
                inputStream = new FileInputStream(fileName);
                inputStream.close();
            }
            catch (FileNotFoundException e){
                System.out.print(fileName);
                break;
            }

        }
        in.close();
    }
}

package com.javarush.test.level20.lesson02.task02;

import javax.xml.crypto.Data;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метоl
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(users.size());
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            for (User asset : users) {
                printWriter.println(asset.getFirstName());
                printWriter.println(asset.getLastName());
                printWriter.println(format.format(asset.getBirthDate()));
                printWriter.println(asset.isMale());
                printWriter.println(asset.getCountry());
            }
            printWriter.flush();

        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int asSize = Integer.parseInt(reader.readLine());
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            for (int i = 0; i < asSize; i++)
            {
                String FasName = reader.readLine();
                String LasName = reader.readLine();
                Date date = format.parse(reader.readLine());
                Boolean is = Boolean.parseBoolean(reader.readLine());
                users.add(new User());
                User a  = users.get(users.size()-1);
                a.setCountry(User.Country.valueOf(reader.readLine()));
                a.setFirstName(FasName);
                a.setLastName(LasName);
                a.setBirthDate(date);
                a.setMale(is);
            }
            reader.close();
        }


    }
}

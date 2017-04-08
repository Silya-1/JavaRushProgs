package com.javarush.test.level20.lesson02.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Читаем и пишем в файл: Human
Реализуйте логику записи в файл и чтения из файла для класса Human
Поле name в классе Human не может быть пустым
В файле your_file_name.tmp может быть несколько объектов Human
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

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception
        {
            //implement this method - реализуйте этот метод
            if (name == null || name.isEmpty()) {
                return;
            }
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(name);
            printWriter.println(assets.size());
            for (Asset asset : assets) {
                printWriter.println(asset.getName());
                printWriter.println(asset.getPrice());
            }
            printWriter.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            name = reader.readLine();
            int asSize = Integer.parseInt(reader.readLine());
            for (int i = 0; i < asSize; i++)
            {
                String asName = reader.readLine();
                String asPrice = reader.readLine();
                assets.add(new Asset(asName));
                assets.get(assets.size()-1).setPrice(Double.parseDouble(asPrice));
            }
            reader.close();
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Human human = (Human) o;
            if (!name.equals(human.name))
                return false;
            if (assets.size() != human.assets.size())
                return false;
            for (int i = 0; i < assets.size(); i++) {
                if (!assets.get(i).getName().equals(human.assets.get(i).getName()))
                    return false;
                if (assets.get(i).getPrice() != human.assets.get(i).getPrice())
                    return false;
            }
            return true;
        }
    }
}

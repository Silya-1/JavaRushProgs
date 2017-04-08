package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws IOException
    {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream sd = new FileInputStream(new File(reader.readLine()));
        Properties pr = new Properties();
        pr.load(sd);
        sd.close();
        reader.close();
        for (Map.Entry<Object, Object> mp : pr.entrySet())
            properties.put(mp.getKey().toString(), mp.getValue().toString());

    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties saveProperties = new Properties();
        for (Map.Entry<String, String> prop : properties.entrySet())  //проходимся по mape
            saveProperties.setProperty(prop.getKey(), prop.getValue());
        saveProperties.save(outputStream, null);

    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties pr = new Properties();
        pr.load(inputStream);
        for (Map.Entry<Object, Object> mp : pr.entrySet())
            properties.put(mp.getKey().toString(), mp.getValue().toString());
    }
}

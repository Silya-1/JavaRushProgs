package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        while (!(s.equals("exit")))
        {
            new ReadThread(s).start();
            s = reader.readLine();
        }
        reader.close();
    }

    public static class ReadThread extends Thread {
        public ReadThread(String fileName) {
            //implement constructor body
            try
            {
              this.inputStream =  new FileInputStream(fileName);
                this.name = fileName;

            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        public FileInputStream inputStream;
        public String name;
        public void run()
        {
            Map<Integer,Integer> list = new HashMap<Integer,Integer>();
            try
            {
                while (inputStream.available()>0){
                    int key = inputStream.read();
                    if(list.containsKey(key)){
                        int value = list.get(key);
                        list.put(key, ++value);
                    }
                    else list.put(key, 1);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            int max = Integer.MIN_VALUE;
            for(Map.Entry<Integer, Integer> l : list.entrySet())
            {
                if(l.getValue() > max)
                    max = l.getValue();
            }
            for(Map.Entry<Integer, Integer> l : list.entrySet())
            {
                if(l.getValue() == max)
                    resultMap.put(this.name, l.getKey());
            }
            try
            {
                inputStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        // implement file reading here - реализуйте чтение из файла тут
    }
}

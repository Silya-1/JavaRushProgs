package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = br.readLine();
        br.close();
        String tag = args[0];//String tag = "span";
        String inTag = "<" + tag;
        String outTag = "</" + tag + ">";
        int lenghtInTag = ("<" + tag).length();
        int lenghtOutTag = ("</" + tag + ">").length();
        BufferedReader reader = new BufferedReader(new FileReader(fileName1));
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> print = new ArrayList<>();
        int place = 0;
        //Инициализация
        boolean start = false;
        String lineIn = "";
        int countDeep = 0;// счетчик глубины вложенности  тегов
        while ((lineIn = reader.readLine()) != null)
        {
            for (int i = 0; i < lineIn.length(); i++)
            {
                if (((i + lenghtInTag) <= lineIn.length())
                        &&    (inTag.equals(lineIn.substring(i, i + lenghtInTag))))// нашли  начало тега
                {
                    lines.add(countDeep,"");
                    countDeep++;
                    start = true;
                }
                if (((i + lenghtOutTag) <= lineIn.length())
                        && (outTag.equals(lineIn.substring(i, i + lenghtOutTag))))
                // нашли конец тега
                {
                    countDeep--;
                    print.add(lines.get(countDeep));
                    lines.remove(countDeep);
                    if(countDeep == 0 && start )
                    {
                        String swap = print.get(print.size() - 1);
                        print.remove(print.size() - 1);
                        print.add(place, swap);
                        place = print.size();
                    }
                }
                for (int y = 0; y < countDeep; y++) // заполняем все строки выше глубины вложенности поcимвольно
                {
                    String s = lines.get(y);
                    lines.remove(y);
                    lines.add(y, s + lineIn.substring(i, i + 1));
                }
            }//C:\Users\Силя\Desktop\test\1.txt
        }
        for (int i = 0; i < print.size(); i++)
        {
            if (print.get(i) != "")
            {
                    System.out.println(print.get(i) + outTag);
            }
        }
        reader.close();
    }
}

package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        Queue<File> queue = new PriorityQueue<>();
        List<String> fileList = new ArrayList<>();
        File file = new File(root);
        queue.add(file);
        while (!queue.isEmpty())
        {
            File queueFile = queue.poll();
            if(queueFile.isDirectory())
            {
                for(File addFile : queueFile.listFiles())
                {
                    queue.add(addFile);
                }
            }else if(queueFile.isFile())
            {
                fileList.add(queueFile.getAbsolutePath());
            }
        }
        return fileList;

    }
}

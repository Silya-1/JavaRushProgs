package com.javarush.test.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by silya on 04.09.2016.
 */
public class HTMLFileFilter extends FileFilter
{
    @Override
    public boolean accept(File f)
    {
        String file = f.getName().toLowerCase();
        return f.isDirectory() ||  file.endsWith(".html") || file.endsWith(".htm");
    }

    @Override
    public String getDescription()
    {
        return "HTML и HTM файлы";
    }
}

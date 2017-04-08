package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by silya on 11.09.2016.
 */
public class FileBucket
{
    Path path;

    public FileBucket()
    {
        try
        {
            this.path = Files.createTempFile("tmp", null);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize()
    {
        return path.toFile().length();
    }

    public void putEntry(Entry entry) {
        try {
            FileOutputStream fos = new FileOutputStream(path.toFile());
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(entry);
            fos.close();
            outputStream.close();
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {
        Entry entry = null;
        if(path.toFile().length() > 0) {
            try
            {
                FileInputStream fis = new FileInputStream(path.toFile());
                ObjectInputStream inputStream = new ObjectInputStream(fis);
                entry = (Entry)inputStream.readObject();
                fis.close();
                inputStream.close();
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
        return entry;
    }

    public void remove()
    {
        try {
            Files.delete(path);
        }
        catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}

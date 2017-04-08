package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by silya on 10.09.2016.
 */
public class Solution
{
    public static Set<Long> getIds(Shortener shortener, Set<String> strings)
    {
        Set<Long> set = new HashSet<>();
        for (String s : strings)
        {
            set.add(shortener.getId(s));
        }
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> resultSet = new HashSet<>();

        for (Long l : keys) {
            resultSet.add(shortener.getString(l));
        }
        return resultSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber)
    {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> testSetStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSetStrings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Set<Long> idsSet;
        Date startTime1 = new Date();
        idsSet = getIds(shortener, testSetStrings);
        Date finishTime1 = new Date();
        long timeDelay =  (finishTime1.getTime() - startTime1.getTime());
        Helper.printMessage(Long.toString(timeDelay));

        Set<String> stringSet;
        Date startTime2 = new Date();
        stringSet = getStrings(shortener, idsSet);
        Date finishTime2 = new Date();
        timeDelay =  (finishTime2.getTime() - startTime2.getTime());
        Helper.printMessage(Long.toString(timeDelay));

        if (testSetStrings.equals(stringSet)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
    public static void main(String[] args)
    {
        StorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 10000);
        OurHashMapStorageStrategy strategy2 = new OurHashMapStorageStrategy();
        testStrategy(strategy2, 10000);
        FileStorageStrategy fileStorageStrategy = new FileStorageStrategy();
        testStrategy(fileStorageStrategy, 200);
        OurHashBiMapStorageStrategy ourHashBiMapStorageStrategy = new OurHashBiMapStorageStrategy();
        testStrategy(ourHashBiMapStorageStrategy, 10000);
        HashBiMapStorageStrategy biMapStorageStrategy = new HashBiMapStorageStrategy();
        testStrategy(biMapStorageStrategy, 10000);
        DualHashBidiMapStorageStrategy dualHashBidiMapStorageStrategy = new DualHashBidiMapStorageStrategy();
        testStrategy(dualHashBidiMapStorageStrategy, 10000);
    }
}

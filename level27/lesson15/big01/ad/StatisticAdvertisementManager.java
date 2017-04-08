package com.javarush.test.level27.lesson15.big01.ad;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Силя on 02.08.2016.
 */
public class StatisticAdvertisementManager
{
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance()
    {
        return ourInstance;
    }

    private StatisticAdvertisementManager()
    {
    }

    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public Map<String, Integer> getAllAdvertisement(){
        Map<String, Integer> result = new TreeMap<String, Integer>(new Comparator()
        {
            @Override
            public int compare(Object s0, Object s1)
            {
                if (s0.toString().toLowerCase().compareTo(s1.toString().toLowerCase()) != 0) return s0.toString().toLowerCase().compareTo(s1.toString().toLowerCase());
                else return s0.toString().compareTo(s1.toString());
            }
        });
        for (Advertisement video : storage.list()) {
            result.put(video.getName(), video.getHits());
        }
        return result;
    }
}

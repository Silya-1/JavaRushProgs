package com.javarush.test.level26.lesson15.big01;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Силя on 25.07.2016.
 */
public class CurrencyManipulatorFactory
{
    static Map<String, CurrencyManipulator> map = new HashMap<>();//мб public

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
    {
        if(map.containsKey(currencyCode))
        {
            return map.get(currencyCode);
        }
        else
        {
            CurrencyManipulator manipulator = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, manipulator);
            return manipulator;
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators()
    {
        return map.values();
    }

    private CurrencyManipulatorFactory()
    {
    }
}

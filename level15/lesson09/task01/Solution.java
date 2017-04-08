package com.javarush.test.level15.lesson09.task01;

import java.util.HashMap;
import java.util.Map;

/* Статики 1
В статическом блоке инициализировать labels 5 различными парами.
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();
    static
    {
        labels.put((double) 12,"Ds");
        labels.put(32.0,"De");
        labels.put(42.0,"Dvv");
        labels.put(25.9,"cD");
        labels.put(7.9,"Dv");
    }


    public static void main(String[] args) {
        System.out.println(labels);
    }
}

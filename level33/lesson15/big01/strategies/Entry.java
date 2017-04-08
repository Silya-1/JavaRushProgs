package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by silya on 10.09.2016.
 */
public class Entry<key, value> implements Serializable
{
    int hash;
    Long key;
    String value;
    Entry<key, value> next;

    public Entry(int hash, Long key, String value, Entry<key, value> next)
    {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Long getKey() {
        return key;
    }


    public String getValue() {
        return value;
    }

    public int hashCode()
    {
        return  Objects.hashCode(value) + hash + hash + (hash << 10) + (hash >> 6);
    }


}

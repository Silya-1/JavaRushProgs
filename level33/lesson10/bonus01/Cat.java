package com.javarush.test.level33.lesson10.bonus01;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by silya on 07.09.2016.
 */
@XmlType(name="cat")
@XmlRootElement
public class Cat
{
    public int res = 5;
    String age = "^^^^";
}

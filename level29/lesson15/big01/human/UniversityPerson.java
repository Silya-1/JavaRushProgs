package com.javarush.test.level29.lesson15.big01.human;

/**
 * Created by silya on 09.08.2016.
 */
public class UniversityPerson extends Human
{
    private University university;

    public UniversityPerson(String name, int age)
    {
        super(name, age);
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public University getUniversity() {
        return university;
    }
}

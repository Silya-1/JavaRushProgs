package com.javarush.test.level17.lesson10.home02;

/* Comparable
Реализуйте интерфейс Comparable<Beach> в классе Beach, который будет использоваться нитями.
*/

public class Beach implements  Comparable<Beach>{
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public synchronized int compareTo(Beach o)
    {
        if (this.getQuality() != o.getQuality()) return  o.getQuality() > this.getQuality()? -1 : 1;
        if (this.getDistance() != o.getDistance()) return this.getDistance() > o.getDistance() ? -1 : 1;
        return 0;
    }

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public  int getQuality() {
        return quality;
    }

    public synchronized void  setQuality(int quality) {
        this.quality = quality;
    }
}

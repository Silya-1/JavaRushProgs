package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by Силя on 12.07.2016.
 */
public class Hippodrome
{
    public static Hippodrome game;
    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    private static ArrayList<Horse> horses = new ArrayList<>();
    public static void main(String[] args)
    {
        game = new Hippodrome();
        Horse a = new Horse("A",3,0);
        Horse b = new Horse("B",3,0);
        Horse c = new Horse("C",3,0);
        horses.add(a);
        horses.add(b);
        horses.add(c);
        game.run();
        game.printWinner();
    }
    public void run()
    {
        for (int i = 1; i <= 100; i++) {

            move();
            print();
            try
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }
    public void print()
    {
        for (Horse horse : horses)
            horse.print();
        for(int i = 0; i < 3; i++)
            System.out.println();
    }
    public void move()
    {
        for (Horse horse : horses)
            horse.move();

    }

    public Horse getWinner()
    {
        double res = 0;
        Horse winner = null;
        for(Horse horse : horses)
            if(horse.getDistance() > res)
            {
                res = horse.getDistance();
                winner = horse;

            }
        return winner;
    }
    public void printWinner()
    {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}

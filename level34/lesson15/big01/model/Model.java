package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;

/**
 * Created by silya on 14.09.2016.
 */
public class Model
{
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("src/com/javarush/test/level34/lesson15/big01/res/levels.txt"));

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void restartLevel(int level)
    {
        this.gameObjects = levelLoader.getLevel(level);
    }

    public void restart()
    {
        restartLevel(currentLevel);
    }

    public void startNextLevel()
    {
        currentLevel = currentLevel + 1;
        restartLevel(currentLevel);
    }

    

    private EventListener eventListener;

    public static int FIELD_SELL_SIZE = 20;

    public void move(Direction direction)
    {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction)) {
            return;
        }
        if (checkBoxCollision(direction)){
            return;

        }

        int sellSize = FIELD_SELL_SIZE;
        switch (direction) {
            case LEFT:
                player.move(-sellSize, 0);
                break;
            case RIGHT:
                player.move(sellSize, 0);
                break;
            case UP:
                player.move(0, -sellSize);
                break;
            case DOWN:
                player.move(0, sellSize);
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        for (Wall wall : gameObjects.getWalls()){

            if(gameObject.isCollision(wall, direction)){
                return true;
            }
        }
        return false;
    }


    public boolean checkBoxCollision(Direction direction){

        Player player = gameObjects.getPlayer();

        GameObject  stoped = null;
        for (GameObject gameObject: gameObjects.getAll()){
            if (!(gameObject instanceof Player)&&!(gameObject instanceof Home) && player.isCollision(gameObject, direction)){
                stoped = gameObject;
            }
        }
        if ((stoped == null)){
            return false;
        }
        if (stoped instanceof Box){
            Box stopedBox = (Box)stoped;
            if (checkWallCollision(stopedBox, direction)){
                return true;
            }
            for (Box box : gameObjects.getBoxes()){
                if(stopedBox.isCollision(box, direction)){
                    return true;
                }
            }
            switch (direction)
            {
                case LEFT:
                    stopedBox.move(-FIELD_SELL_SIZE, 0);
                    break;
                case RIGHT:
                    stopedBox.move(FIELD_SELL_SIZE, 0);
                    break;
                case UP:
                    stopedBox.move(0, -FIELD_SELL_SIZE);
                    break;
                case DOWN:
                    stopedBox.move(0, FIELD_SELL_SIZE);
            }
        }
        return false;

    }


    public void checkCompletion()
    {
        boolean yes = true;
        for(Home home : gameObjects.getHomes()){
            boolean currentyes = false;

            for (Box box: gameObjects.getBoxes()){
                if ((box.getX() == home.getX()) && (box.getY() == home.getY()))
                    currentyes = true;
            }

            if (!currentyes)
                yes = false;
        }
        if (yes)
            eventListener.levelCompleted(currentLevel);
    }
}

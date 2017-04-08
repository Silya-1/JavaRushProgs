package com.javarush.test.level36.lesson04.big01.model;

/**
 * Created by silya on 17.09.2016.
 */
public interface Model
{
    ModelData getModelData();

    void loadUsers();

    void loadDeletedUsers();

    void  deleteUserById(long id);

    void loadUserById(long userId);

    void changeUserData(String name, long id, int level);

}

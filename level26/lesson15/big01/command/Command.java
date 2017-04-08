package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

/**
 * Created by Силя on 26.07.2016.
 */
interface Command
{
    public void execute() throws InterruptOperationException;
}

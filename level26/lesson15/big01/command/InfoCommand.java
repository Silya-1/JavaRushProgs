package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Силя on 26.07.2016.
 */
class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> manipulatorMap = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (manipulatorMap.isEmpty())
            ConsoleHelper.writeMessage("No money available.");
        else
        {
            int count = 0;
            for (CurrencyManipulator manipulator : manipulatorMap)
            {
                if (manipulator.hasMoney() && manipulator.getTotalAmount()>0)
                {
                    ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
                    count++;
                }
            }
            if (count==0)
                ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}


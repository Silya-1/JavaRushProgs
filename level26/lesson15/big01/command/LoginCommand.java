package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Силя on 28.07.2016.
 */
class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true)
        {

            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String card = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();
            if (validCreditCards.containsKey(card))
            {
                if(validCreditCards.getString(card).equals(pin))
                {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), card));
                    break;
                }else
                {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), card));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    continue;
                }
            }
            else
            {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), card));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
        }
    }
}

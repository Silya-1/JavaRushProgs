package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Силя on 26.07.2016.
 */
class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int sum;
        ConsoleHelper.writeMessage(res.getString("before"));
        while(true)
        {
            String s = ConsoleHelper.readString();
            try
            {
                sum = Integer.parseInt(s);
            } catch (NumberFormatException e)
            {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                continue;
            }
            if (sum <= 0)
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (!currencyManipulator.isAmountAvailable(sum))
            {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }
            try
            {
                Map<Integer, Integer> withdrawMoney =  currencyManipulator.withdrawAmount(sum);
                for (Map.Entry<Integer, Integer> x : withdrawMoney.entrySet())
                {
                    System.out.println("\t" + x.getKey() + " - " + x.getValue());
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, currencyCode));
                break;

            } catch (NotEnoughMoneyException e)
            {
                ConsoleHelper.writeMessage("exact amount is not available");
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
        }

    }
}

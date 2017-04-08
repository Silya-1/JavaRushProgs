package com.javarush.test.level26.lesson15.big01;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by Силя on 25.07.2016.
 */
public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    //вывод сообщения пользователю
    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    //считывание сообщения от пользователя
    public static String readString() throws InterruptOperationException
    {
        String message = "";
        try
        {
            message = reader.readLine();
            if (message.equalsIgnoreCase(res.getString("operation.EXIT")))
                throw new InterruptOperationException();
        }
        catch (IOException ignored)
        {
        }
        return message;
    }

    //считываем код валюты
    public static String askCurrencyCode() throws InterruptOperationException
    {
        writeMessage(res.getString("choose.currency.code"));
        String value = "";
        while (true)
        {

            value = readString();
            if (value.length() == 3)
                break;
            else
                writeMessage(res.getString("invalid.data"));
        }
        return value.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode)  throws InterruptOperationException
    {
        String[] array = null;
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));

        while (true)
        {
            String s = readString();
            array = s.split(" ");
            int k;
            int l;
            try
            {
                k = Integer.parseInt(array[0]);
                l = Integer.parseInt(array[1]);
            }
            catch (Exception e)
            {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            if (k <= 0 || l <= 0 || array.length > 2)
            {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return  array;
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        boolean correctInput = false;
        Operation chosenOperation = Operation.INFO;
        while (!correctInput) {
            ConsoleHelper.writeMessage(res.getString("choose.operation") + " \n" +
                    res.getString("operation.INFO") + ": 1\n" +
                    res.getString("operation.DEPOSIT") + ": 2\n" +
                    res.getString("operation.WITHDRAW") + ": 3\n" +
                    res.getString("operation.EXIT") + ": 4");
            String input = readString();
            int choise;
            try {
                choise = Integer.valueOf(input.trim());
                chosenOperation = Operation.getAllowableOperationByOrdinal(choise);
                correctInput = true;
            } catch (IllegalArgumentException e) {ConsoleHelper.writeMessage(res.getString("invalid.data")); }
        }
        return chosenOperation;
    }

    public static void printExitMessage()
    {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }

}


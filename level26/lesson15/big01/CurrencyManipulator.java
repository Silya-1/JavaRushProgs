package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by Силя on 25.07.2016.
 */
public class CurrencyManipulator// класс манипулятор, содержит информациюо валюте номинале и количестве купюр
{
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count)
    {
        if(denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else
            denominations.put(denomination,count);
    }
    public int getTotalAmount()
    {
        int result = 0;
        for(Map.Entry<Integer,Integer> pair : denominations.entrySet())
            result = result + (pair.getKey() * pair.getValue());

        return result;
    }

    public boolean hasMoney(){
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount)
    {
        if (getTotalAmount() >= expectedAmount) return true;
        else return false;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        // копия старой мапы, в которой еще не меняли значения, его храним, если окажется, что нам надо бросать NotEnoughMoneyException
        Comparator<Integer> compare = new Comparator<Integer>()
        {

            @Override
            public int compare(Integer o1, Integer o2)
            {
                return (o1 > o2) ? -1 : ((o1 == o2) ? 0 : 1);
            }
        };
        Map<Integer, Integer> copyOfDenominations = new TreeMap<Integer, Integer>(compare);
        copyOfDenominations.putAll(denominations);
        int withdrawMoney = expectedAmount; //количество снимаемых денег (остаток)
        //мапа с результом с сортировка от большего к меньшему
        Map<Integer, Integer> result = new TreeMap<Integer, Integer>(compare);
        for (Map.Entry<Integer, Integer> x : denominations.entrySet())
        {
            int currentBanknote = x.getKey(); //номинал банкнот
            int remainingBanknotes = x.getValue(); //количество банкнот
            int countOfCurrentBanknotes = 0; //число текущих банкнот, которые следует выдать
            //если кол-во банкнот данного номинала и остаток от снятия в случае удачно операции >=0, то...
            while ((remainingBanknotes > 0) && ((withdrawMoney - currentBanknote) >= 0))
            {
                //изменяем остаток
                withdrawMoney -= currentBanknote;
                //изменяем кол-во оставшихся банкнот в мапе
                x.setValue(--remainingBanknotes);
                countOfCurrentBanknotes++;
            }
            //заносим в мапу "результ" наши банкноты текущего номинала
            if (countOfCurrentBanknotes > 0)
            {
                result.put(currentBanknote, countOfCurrentBanknotes);
            }
        }
        //ЕЩЕ разок проверяем если остаток>0 и при этом у нас в мапе еще остались деньги, то это означает, что
        //данными банкнотами невозможно выдать запрашиваемую сумму, кидаем эксепшен
        if ((withdrawMoney > 0) && (withdrawMoney < getTotalAmount()))
        {
            //нужно как-то откатить на старую версию denominations
            denominations.clear();
            denominations.putAll(copyOfDenominations);
            //это все если данными купюрами не можем выдать
            throw new NotEnoughMoneyException();
        }
        return result;
    }
}

/*
4. В классе CurrencyManipulator создайте метод void addAmount(int denomination, int count),
который добавит введенные номинал и количество банкнот

 */
package com.javarush.test.level19.lesson03.task05;

import java.util.HashMap;
import java.util.Map;

/* Закрепляем адаптер
Адаптировать Customer и Contact к RowItem.
Классом-адаптером является DataAdapter.
Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada
*/

public class Solution {
    private static Map<String,String> countries = new HashMap<String,String>();
    static
    {
        countries.put("Ukraine", "UA");
        countries.put("Russia", "RU");
        countries.put("Canada", "CA");
    }

    public static class DataAdapter implements RowItem{

        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.contact = contact;
            this.customer = customer;
        }

        public String getCountryCode()
        {
            return countries.get(customer.getCountryName());
        };

        public String getCompany()
        {
            return customer.getCompanyName();
        };

        public String getContactFirstName()
        {
            return contact.getName().split(",")[1].trim();
        };

        public String getContactLastName()
        {
            return contact.getName().split(",")[0].trim();
        };

        public String getDialString()
        {
            String dial = contact.getPhoneNumber();
            String s = dial.replaceAll("-", "");
            s = s.replace("(", "");
            s = s.replace(")", "");
            return "callto://" + s;
        };
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}
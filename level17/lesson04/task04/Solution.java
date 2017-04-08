package com.javarush.test.level17.lesson04.task04;

/* Синхронизированный президент
И снова Singleton паттерн - синхронизация в статическом блоке
Внутри класса OurPresident в статическом блоке создайте синхронизированный блок.
Внутри синхронизированного блока инициализируйте president.
*/

import java.io.IOException;

public class Solution {
    public static class OurPresident {

        static
        {
            try
            {
                synchronized (OurPresident.class)
                {
                    president = new OurPresident();
                }
            }catch (Exception a){}

        }

        private static OurPresident president;

        private OurPresident() {
        }

        public static OurPresident getOurPresident() {
            return president;
        }
    }
}

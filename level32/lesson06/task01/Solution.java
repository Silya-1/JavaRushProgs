package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        for(int i = 0; i < 3; i++)
        {
            byteArrayOutputStream.write(48 + (int)(Math.random() * 10));
            byteArrayOutputStream.write(97 + (int)(Math.random() * 26));
            if(i < 2)
                byteArrayOutputStream.write(65 + (int)(Math.random() * 26));
        }
        return byteArrayOutputStream;
    }
}

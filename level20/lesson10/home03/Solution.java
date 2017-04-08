package com.javarush.test.level20.lesson10.home03;

import java.io.*;

/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
Метод main не участвует в тестировании.
*/
public class Solution {
    public static class A {
        protected String name = "A";

        public A(String name) {
            this.name += name;
        }
        public A(){}
    }

    public class B extends A implements Serializable {
        public B(String name) {
            super(name);
            this.name += name;
        }
        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeObject(name);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {name = (String)in.readObject();}
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Solution s = new Solution();
        B b = s.new B("B");
        System.out.println(b.name);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Силя\\Desktop\\test\\1.txt"));
        objectOutputStream.writeObject(b);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\Силя\\Desktop\\test\\1.txt"));
        B bDes;
        bDes = (B) objectInputStream.readObject();
        System.out.println("b:    " + b.toString() + " " + b.name);
        System.out.println("bDes: " + bDes.toString() + " " + bDes.name);
        objectInputStream.close();
    }
}

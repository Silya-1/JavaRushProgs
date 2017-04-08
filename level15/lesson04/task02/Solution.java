package com.javarush.test.level15.lesson04.task02;

/* ООП - Перегрузка
Перегрузите метод printMatrix 8 различными способами. В итоге должно получиться 10 различных методов printMatrix.
*/

public class Solution {
    public static void main(String[] args)
    {
        printMatrix(2, 3, "8");
    printMatrix(2, 3, "8");
    printMatrix((Integer)2, 3, "8");
    printMatrix(2, (Integer)3, "8");
    printMatrix((Integer)2, (Integer)3, "8");
    printMatrix("8", 2, 3);
    printMatrix("8", (Integer)2, 3);
    printMatrix("8", 2, (Integer)3);
    printMatrix(2, "8", 3);
    printMatrix((Integer) 2, "8", 3);
    printMatrix(2, "8", (Integer)3);
    };
    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
       // printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(Integer m, Integer n, String value) {
        System.out.println("Заполняем объектами String");
        //printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(Integer m, int n, String value) {
        System.out.println("Заполняем объектами String");
        //printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(int m, Integer n, String value) {
        System.out.println("Заполняем объектами String");
        //printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(String value, int m, int n) {
        System.out.println("Заполняем объектами String");
       // printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(String value, Integer m, int n) {
        System.out.println("Заполняем объектами String");
       // printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(String value, int m, Integer n) {
        System.out.println("Заполняем объектами String");
        //printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(int m, String value, int n) {
        System.out.println("Заполняем объектами String");
        //printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(Integer m, String value, int n) {
        System.out.println("Заполняем объектами String");
       // printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(int m, String value, Integer n) {
        System.out.println("Заполняем объектами String");
       // printMatrix(m, n, (Object) value);
    }
}

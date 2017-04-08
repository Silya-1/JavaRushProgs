package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {
    protected Solution(String a) {}
    protected Solution(Double a) {}
    protected Solution(Integer a) {}

    public Solution() {}
    public Solution(double a, double b) {}
    public Solution(Object pub_obj) {}

    Solution(int a, String b) {}
    Solution(String a, int b) {}
    Solution(Double a, double b) {}

    private Solution(Integer a, Integer c){}
    private Solution(Float a, Integer c){}
    private Solution(Double a, Integer c){}
}


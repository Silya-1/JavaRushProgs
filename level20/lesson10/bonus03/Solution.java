package com.javarush.test.level20.lesson10.bonus03;

import java.util.LinkedList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args)
    {
        int[][] crossword = new int[][]{
                {'e', 'd', 'e', 'e', 'l', 'k'},
                {'s', 'a', 'm', 'm', 's', 'o'},
                {'o', 'o', 'g', 'r', 'a', 'v'},
                {'h', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 's', 'a', 'm', 'e'}
        };
        List<Word> list = detectAllWords(crossword, "home", "same");
        System.out.println((char)crossword[3][0]);
        for (Word word : list)
        {
            System.out.println(word.toString()+"klll");
        }
    }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */

    public static List<Word> detectAllWords(int[][] crossword, String... words) {

        List<Word> list = new LinkedList<>();
        for (String word : words)
        {
            System.out.println(word);
            char[] array = new char[word.length()];
            for (int i = 0; i < crossword.length; i++)
            {
                for(int j = 0; j < crossword[i].length; j++)
                {
                 /*------------find vertical--------------------*/
                    if(i + word.length() <= crossword.length)
                    {
                        for(int c = 0; c < word.length(); c++)
                            array[c] = (char)crossword[i + c][j];
                        if(String.valueOf(array).equals(word))
                        {
                            Word word1 = new Word(word);
                            word1.setStartPoint(j , i);
                            word1.setEndPoint(j ,i + word.length() - 1);
                            list.add(word1);
                        }
                        else
                        {
                            StringBuilder str = new StringBuilder(String.valueOf(array));
                            str.reverse();
                            if(str.toString().equals(word))
                            {
                                Word word1 = new Word(word);
                                word1.setStartPoint(j, i + word.length() - 1);
                                word1.setEndPoint(j, i);
                                list.add(word1);
                            }
                        }
                    }
                    /*------------find horizontical--------------------*/
                    if(j + word.length() <= crossword[i].length)
                    {
                        for(int c = 0; c < word.length(); c++)
                            array[c] = (char)crossword[i][j + c];
                        if(String.valueOf(array).equals(word))
                        {
                            Word word1 = new Word(word);
                            word1.setStartPoint(j , i);
                            word1.setEndPoint(j  + word.length() - 1 ,i);
                            list.add(word1);
                        }
                        else
                        {
                            StringBuilder str = new StringBuilder(String.valueOf(array));
                            str.reverse();
                            if(str.toString().equals(word))
                            {
                                Word word1 = new Word(word);
                                word1.setStartPoint(j  + word.length() - 1, i);
                                word1.setEndPoint(j, i);
                                list.add(word1);
                            }
                        }
                    }
                    // ------------find rightdiagonl--------------------
                    if((j + word.length() <= crossword[0].length) && (i + word.length() <= crossword.length))
                    {
                        for(int c = 0; c < word.length(); c++)
                            array[c] = (char)crossword[i + c][j + c];
                        if(String.valueOf(array).equals(word))
                        {
                            Word word1 = new Word(word);
                            word1.setStartPoint(j , i);
                            word1.setEndPoint(j + word.length() - 1  ,i  + word.length() - 1);
                            list.add(word1);
                        }
                        else
                        {
                            StringBuilder str = new StringBuilder(String.valueOf(array));
                            str.reverse();
                            if(str.toString().equals(word))
                            {
                                Word word1 = new Word(word);
                                word1.setStartPoint(j  + word.length() - 1, i + word.length() - 1);
                                word1.setEndPoint(j, i);
                                list.add(word1);
                            }
                        }
                    }
                     //------------find rightdiagonl--------------------
                    if(j - word.length() >= -1 && i + word.length() <= crossword.length)
                    {
                        for(int c = 0; c < word.length(); c++)
                            array[c] = (char)crossword[i + c][j - c];
                        System.out.println(String.valueOf(array));
                        if(String.valueOf(array).equals(word))
                        {
                            Word word1 = new Word(word);
                            word1.setStartPoint(j , i);
                            word1.setEndPoint(j - word.length() - 1  ,i  + word.length() - 1);
                            list.add(word1);
                        }
                        else
                        {
                            StringBuilder str = new StringBuilder(String.valueOf(array));
                            str.reverse();
                            if(str.toString().equals(word))
                            {
                                Word word1 = new Word(word);
                                word1.setStartPoint(j  - word.length() - 1, i + word.length() - 1);
                                word1.setEndPoint(j, i);
                                list.add(word1);
                            }
                        }
                    }
                }
            }
        }

        return list;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}

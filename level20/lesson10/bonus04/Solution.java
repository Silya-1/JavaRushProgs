package com.javarush.test.level20.lesson10.bonus04;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
*/
public class Solution extends AbstractList<String> implements List<String>, Cloneable, Serializable
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {

        List<String> list = new Solution();

        for (int i = 1; i < 12; i++)
        {
            list.add(String.valueOf(i));
        }
        System.out.println(((Solution) list).size() + "size");
        ((Solution) list).remove("3");
        System.out.println(((Solution) list).size() + "size");
       ((Solution) list).root.print();
    }

    public int size = 0;
    public  int max_level = 1;
    public  Node root = new Node("root", null, null, null, 0);
    public boolean isfound;
    public int num = 0;

    @Override
    public int size()
    {
        return size;
    }

    public void clear()
    {
        root.left = null;
        root.right = null;
        size = 0;
    }

    public boolean remove(Object o) {
        root.delete(String.valueOf(o));
        max_level = 0;
        size = -1;
        root.check_max_level();
        return true;
    }

    public boolean delete(Object o) {
        return remove(o);
    }


    public boolean add(String s) {
        if(root.insert(s))
            return true;
        else{
            max_level++;
            return root.insert(s);
        }
    }

    public void print()
    {
       root.print();
    }

    public  class Node implements Serializable
    {
        String data;
        int level;
        Node left;
        Node right;
        Node parent;
        int place = 0;

        public void check_max_level()
        {
            try
            {
                if(this != null)
                    size++;
                if(left != null)left.check_max_level();
                if (right != null)right.check_max_level();
                if(this.level > max_level)
                {
                    max_level = this.level;
                }
            }catch (NullPointerException a){return;}
        }

        public void delete(String s)
        {
            Node child = find(s);
            if (child == null)
                System.out.println("Элемент не найден");
            else {
                Node delParent = child.parent;
                if (delParent.right == child)
                    delParent.right = null;
                else if (delParent.left == child){
                    delParent.left = null;
                    }
                }
        }

        public Node(String s, Node left, Node right, Node parent, int level)
        {
            this.data = s;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.level = level;
        }

        public void print()
        {
            if(left != null)
                left.print();
            if(right != null)
                right.print();
            System.out.println(data);
            return;
        }

        public boolean insert(String s)
        {

            if(level == (max_level - 1) && (left == null || right == null))
            {
                if(left == null){
                    left = new Node(s, null, null, this, level + 1); left.place = ++num;}
                else{
                    right = new Node(s, null, null, this, level + 1);right.place = ++num;}
                size++;
                return true;
            }
            try
            {
                boolean h = false;
                if (left != null)
                {
                    h = left.insert(s);
                }
                if(right != null && !h)
                    h = right.insert(s);
                return  h;
            }catch (NullPointerException a) {return  false;}
        }

        public Node find(String s)
        {
           if(this.data.equals(s))
               return this;
            try
            {
                Node str = null;
                if(left != null)
                    str = left.find(s);
                if(str != null)
                    return str;
                if(right != null)
                    str = right.find(s);
                return str;
            }catch (NullPointerException a){return null;}

        }

        public Node find(int a, int b)
        {
            if(this.place > a && this.place < b)
                return this;
            Node res = null;
            if(left != null)
            {
                res = left.find(a,b);
            }
            if(res != null)
                return res;
            if(right != null)
            {
                res = right.find(a,b);
            }
            return res;
        }

    }

    public String getParent(String value) {
        //have to be implemented
        Node child = root.find(value);
        if(child.parent.parent == null)
            return  null;
        String parentString = child.parent.data;
        return parentString;
    }


    public String get(int index)
    {
        throw new UnsupportedOperationException();
    }


   private class IteratorSolution implements Iterator<String>
   {
       private Node lastReturned;
       private Node next;
       private int nextIndex = 1;

       public IteratorSolution(int index)
       {
           if (root.left != null)
               next = root.left;
           else next = root.right;
           nextIndex = index;
       }

       public boolean hasNext()
       {
           return nextIndex < size;
       }

       public String next()
       {
           if (!hasNext())
               throw new NoSuchElementException();

           lastReturned = next;
           next = getNext(next);
           nextIndex++;
           return lastReturned.data;
       }

       public void remove()
       {
           delete((String) lastReturned.data);
           nextIndex--;
       }

       public  Node getNext(Node next)
       {
           Node res = null;
           int c = next.place + 1;
           while (res == null)
           {
               res = root.find(next.place, c++);
           }
           return res;
       }
   }
}
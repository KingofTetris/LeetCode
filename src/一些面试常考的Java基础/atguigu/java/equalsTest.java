package 一些面试常考的Java基础.atguigu.java;

import JAVA基础_反射.Person;
import LeetCode数据结构基础.day3.链表.ListNode;
import org.junit.Test;

/**
 * @author KingofTetris
 * @File equalsTest
 * @Time 2021/10/29  21:20
 */
public class equalsTest {


    //回顾==的使用
    @Test
    public void test(){

        int a = 10;
        int b = 10;
        float c  = 10;
        double d = 10.0;
        boolean e = true;
        char ch = 10;
        char ch1 = 'A';
        int ch2 = 65;
        ListNode node1 = new ListNode();
        ListNode node2 = new ListNode();
        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(a==d);
        System.out.println(c==d);

        System.out.println();

        System.out.println(ch==a);
        System.out.println(ch==c);

        System.out.println();

        System.out.println(ch1 == ch2);
//        System.out.println(c==e);//基本数据类型都可以用==比较，除了Boolean

        System.out.println();

        System.out.println(node1 == node2); //引用类型比较地址


    }



    /**
     * equals()方法
     * equals是个方法，只能应用于引用数据类型
     * */
    @Test
    public void tese2(){

        /**
         * 如果你没有重写equals方法，那就是object类中的方法，return(this == obj) 与==的作用一样
         * public boolean equals(Object obj) {
         *         return (this == obj);
         *     } */
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        System.out.println(node1.equals(node2));//false


        //因为string 重写了equals方法，所有比较的是内容是否一致
        String str1 = new String("123");
        String str2 = new String("123");
        System.out.println(str1.equals(str2));//true


//        Date date1 = new Date("2222L");
//        Date date2 = new Date("2222L");
//        System.out.println(date1.equals(date2));


        //如果我们自定义的类要进行equals 那就要自己重写
        Person p1 = new Person("alex",21);
        Person p2 = new Person("alex",21);
        System.out.println(p1.equals(p2));//重写后的equals就是true了

    }

}

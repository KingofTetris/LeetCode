package 一些面试常考的Java基础.atguigu.java;

import org.junit.Test;

/**
 * String的使用
 *
 * @author kingofTetris
 * @create 2021 9.11 22：04
 *
 */

/**
 * 不可变性是指旧的内容在方法区里是不会重复生成的，所有的String都指向同一个地址。
 * 不管是直接赋值，拼接，修改，删除等等。只要内容不变地址就是同一个，反之有新内容就有新地址
 *
 * String的赋值
 * String str = "hello";
 * String s1 = new String();
 * String s2 = new String(String original);
 * String s3 = new String(char[] a);
 * String s4 = new String(char[] a,int startIndex,int endIndex);
 *
 *
 * 字面化和new+构造器都会在方法区中的字符串常量池 即char[]。
 * 但new会复制一份到堆(heap)中
 */

public class StringTest {
    public static void main(String[] args){
             String s1 = "abc";
             System.out.println(s1);
    }

    @Test
    public void test1(){

    }

    @Test
    public void test3(){
        /**
         *  字面量之间的拼接 结果还是在常量池中，且常量池不会出现重复的内容
         *  只有拼接了一个变量，结果就在堆中
         *  如果拼接的结果调用了intern()方法，返回值在常量池中
         */
        String s1 = "javaEE";
        String s2 = "hadoop";
        String s3 = "javeEEhadoop";
        String s4 = "javeEE" + "hadoop";
        String s5 = s1 + "hadoop";
        String s6 = "javeEE" + s2;
        String s7 = (s1+s2).intern();
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s3 == s6);
        System.out.println(s4 == s5);
        System.out.println(s4 == s6);
        System.out.println(s5 == s6);
        System.out.println(s7 == s3);
    }
}

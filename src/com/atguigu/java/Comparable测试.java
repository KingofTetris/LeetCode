package com.atguigu.java;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author KingofTetris
 * @File Comparable测试
 * @Time 2021/10/17  15:18
 */
public class Comparable测试 {

    @Test
    public void test(){
        son[] chidren = new son[5];
        for (int i = 0; i < 5; i++) {
            int age = (int) (Math.random()*100);
            chidren[i] = new son("john"+ i,age);
        }

        Arrays.sort(chidren);
        //Arrays.toString(Obj)
        //按照Obj自己实现的toString输出数组
        System.out.println(Arrays.toString(chidren));
    }
}

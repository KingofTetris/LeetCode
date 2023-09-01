package 校招笔试真题.小米.小米2022秋招;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/1
 */


//给你一组从1 - n的数组，下标从1开始，每数到3的倍数，把这个数字删除
//然后继续循环往下数  直到只剩下一个数
//请你返回这个剩下的数字。
public class 约瑟夫环 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }


        /**
         * 基本思路，每3个一组计数 删除第三个数
         * m
         */
        int m = 1;
        while (list.size() != 1) { //当list数量不为1
            for (int i = 0; i < list.size(); i++) {
                if (m > 3) {
                    m = 1;
                }
                if (m % 3 == 0) {
                    list.remove(i);
                    //虽然删掉了元素
                    i--; //i-- i++ 抵消。保证长度不会变。
                }
                m++;
            }
        }
        System.out.println(list);
    }

   /* 作者：想run的打工人all-in校招
    链接：https://www.nowcoder.com/exam/test/72969342/submission?examPageSource=Company&pid=39932492&testCallback=https%3A%2F%2Fwww.nowcoder.com%2Fexam%2Fcompany%3FcurrentTab%3Drecommand%26jobId%3D100%26keyword%3D%E5%B0%8F%E7%B1%B3%26selectStatus%3D0&testclass=%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91
    来源：牛客网    */
}

package 算法设计与分析和助教课.梅努斯算法I.第一次实验;

import java.util.*;

//Reverse an integer Given an input string of a signed 32-bit integer x from the console,
// print out x with its digits reversed. If reversing x causes the value to go outside
//the signed 32-bit integer range [-2^31, 2^31-1], then print out 0.


/* Example#1:
Input:123
Output:321

Example#2:
Input:-256
Output:-652
*/

public class problem1_原 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("please input a number");
        int number = sc.nextInt();
        if (number > Math.pow(2, 31)) {
            System.out.println("0");
        } else {
            System.out.println(bcy(number));
        }
    }

    private static int bcy(int x) {
        int res = 0;
        while (x != 0) {
            int t = x % 10;
            int newres = res * 10 + t; //每次个位*10,加上t;
            //判断溢出的一个办法，newres 是*10 +t而来。如果-t/10不等于原来的res
            //说明发生了溢出返回0即可。
            if ((newres - t) / 10 != res) {
                return 0;
            } else {
                res = newres;
                x = x / 10;
            }
        }
        return res;
    }
}

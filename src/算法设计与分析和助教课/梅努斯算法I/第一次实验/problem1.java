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

public class problem1 {
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
    //1234567803 反转前没溢出
    //3087654321 反转后溢出
    //反转数字。
    private static int bcy(int x) {
        int res = 0;
        int len = 0;
        int temp = x;
        while (temp != 0){
            temp = temp / 10;
            len++;
        }
        //个位是不用进10的 len -1
        int base = (int) Math.pow(10,len - 1);
        while (x != 0){
            int gw = x % 10;
            res += gw * base;
            base = base / 10;//每次/10
            x = x / 10;
        }
        return res;
    }
}

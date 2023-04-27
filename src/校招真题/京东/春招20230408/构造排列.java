package 校招真题.京东.春招20230408;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/26
 * <p>
 * 给定一个长度为n的排列，将其中前k个数排列成一个长度为k的排列，只能进行相邻两个数的交换操作，求最少需要多少次上述操作才能满足题目要求。
 * <p>
 * 输入：
 * <p>
 * 第一行输入两个正整数n和k。
 * <p>
 * 第二行输入n个正整数ai，代表排列。
 * <p>
 * 输出：
 * <p>
 * 一个整数，代表最小的操作次数。
 * <p>
 * 限制：
 * <p>
 * 1 ≤ n ≤ 200000
 * <p>
 * 1 ≤ k ≤ n
 * <p>
 * 1 ≤ ai ≤ n
 * <p>
 * 样例：
 * <p>
 * 输入：
 * <p>
 * 5 3
 * <p>
 * 2 4 1 3 5
 * <p>
 * 输出：
 * <p>
 * 2
 * <p>
 * 样例解释：
 * <p>
 * 第一次交换4和1，数组变成[2, 1, 4, 3, 5]。
 * <p>
 * 第二次交换4和3，数组变成[2, 1, 3, 4, 5]。
 * <p>
 * 此时前3个数构成排列，满足条件。
 */


import java.util.*;
public class 构造排列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //输入整数n
        int k = sc.nextInt(); //输入整数k
        int[] nums = new int[n]; //创建长度为n的整数数组nums
        for (int i = 0; i < n; i++) { //循环n次，输入数组nums的元素
            nums[i] = sc.nextInt();
        }
        if (k == n) { //特判，当k等于n时，输出0
            System.out.println(0);
            return;
        }
        int lo = 0, hi = n - 1; //初始化双指针lo和hi 左右端点
        long ans = 0; //初始化操作次数ans
        while (lo < k && hi >= k) { //循环，直到满足条件lo>=k或hi<k为止
            while (lo < k && nums[lo] <= k) { //从前往后遍历数组nums，直到找到第一个大于k的元素，或者遍历了前k个元素
                lo++;
            }
            while (hi >= k && nums[hi] > k) { //从后往前遍历数组nums，直到找到第一个小于等于k的元素，或者遍历了后n-k个元素
                hi--;
            }
            if (hi > lo) ans += hi - lo; //如果hi比lo大，那么将操作次数ans加上hi-lo
            hi--; //将hi指针向前移动一位
            lo++; //将lo指针向后移动一位
        }
        System.out.println(ans); //输出操作次数ans
    }
}


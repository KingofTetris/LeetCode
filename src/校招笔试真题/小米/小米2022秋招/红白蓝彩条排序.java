package 校招笔试真题.小米.小米2022秋招;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/1
 */


//输入红白蓝(数字为123)序列(无序) 要求你在O(n)内重组序列保证有序

// 给你一个仅有红，白，蓝三种颜色组成的10个条块序列，现需要你将这些条块按照红，白，蓝的顺序排好
// ，可用1代表红色，2代表白色，3代 表蓝色，要求时间复杂度为O(n)。例如，给定彩色条块序列为：
public class 红白蓝彩条排序 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[10];
        for (int i = 0; i < 10; i++) {
            nums[i] = sc.nextInt();
        }
        solution(nums);
    }

    //偷鸡的方法，不用排序，直接计数输出就完了。
    public static void solution(int[] nums){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0) + 1);
        }
        for (int j = 0; j < map.get(1); j++) {
            System.out.print(1 + " ");
        }
        for (int j = 0; j < map.get(2); j++) {
            System.out.print(2 + " ");
        }
        for (int j = 0; j < map.get(3); j++) {
            System.out.print(3 + " ");
        }
    }
}

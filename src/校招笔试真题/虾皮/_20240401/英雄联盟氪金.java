package 校招笔试真题.虾皮._20240401;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/23
 */
public class 英雄联盟氪金 {
    static ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    static ArrayList<Integer> path = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int coins = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        //要保持相对顺序，不能排序
        backTracking(coins,nums,0);
        int max = -1;
        ArrayList<Integer> maxL = new ArrayList<>();
        for (ArrayList<Integer> re : res) {
            int size = re.size();
            if (size > max){
                max = size;
                //选择最靠近coins的那个
                maxL = re;
            }
        }
        //2 12 15 19 3 6 1 2 6 8 7 6 = 87
        //2 29 12 3 6 1 2 6 8 7 6 13 = 95
//        System.out.println(max);
        for (int i = 0; i < maxL.size(); i++) {
             if (i != maxL.size() - 1){
                 System.out.print(maxL.get(i) + " ");
             }
             else {
                 System.out.print(maxL.get(i));
             }
        }
    }

    private static void backTracking(int coins, int[] nums, int startIndex) {
        if (coins == 0 || startIndex > nums.length - 1){
            res.add(new ArrayList<>(path));
            return;
        }

        int len = startIndex;
        for (; len < nums.length; len++) {
            if (coins >= nums[len]){
                path.add(nums[len]);
                coins -= nums[len];
                backTracking(coins,nums,len + 1);
                //回溯
                coins += nums[len];
                path.remove(path.size() - 1);
            }
        }

        //如果到达最后也可以加入这个path。
        if (len == nums.length){
            res.add(new ArrayList<>(path));
        }

    }
}

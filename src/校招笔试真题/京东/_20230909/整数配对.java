package 校招笔试真题.京东._20230909;

import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/9
 */

// 40% 剩下的TLE。 好像就是不能双重for
public class 整数配对 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);//sort以后还是5%
        long res = 0;
        //找两个数差值不超过k res最大是多少
        int[] flags = new int[n];//标记
        int x = 0, y = 0;
        for (int i = n - 1; i >= 0; i--) {
            long temp = 0;
            if (flags[i] == 1) continue;
            for (int j = i - 1; j >= 0; j--) {
                if (flags[j] == 1) continue;
                if (nums[i] - nums[j] > k) break;
                long product = (long) nums[i] * nums[j];
                if ((nums[i] - nums[j]) <= k && product > temp) {
                    temp = product;
                    //记录最大的i,j
                    x = i;
                    y = j;
                }
            }
            //更新本次选中的两个数
            flags[x] = 1;
            flags[y] = 1;
            res += temp;
        }
        System.out.println(res);
    }

}

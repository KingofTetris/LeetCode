package 校招笔试真题.深信服._20240912;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/13
 */
public class 小塔的木材 {

    public static void main(String[] args){
        //就是凑硬币。
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n;i++){
            nums[i] = sc.nextInt();
        }
        int target = sc.nextInt();

        int[] dp = new int[target + 1];

        int max = Integer.MAX_VALUE;
        //完全背包求最少数量的方案，怎么遍历都行
        Arrays.fill(dp, max);
        dp[0] = 0 ;
        //物品
        for(int i = 0; i < n; i++){
            //背包
            for(int j = nums[i];j <= target ; j++ ){
                //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                //否则他+1 就溢出变成MIN_VALUE了
                if (dp[j - nums[i]] != max) {
                    //选择硬币数目最小的情况
                    //每次选择一个硬币就让dp[j - nums[i]] + 1
                    dp[j] = Math.min(dp[j], dp[j - nums[i]] + 1);
                }
            }
            //背包从1-9的变化
//            System.out.println(Arrays.toString(dp));
        }
        System.out.println(Arrays.toString(dp));
        int res = dp[target] == max ? -1 : dp[target];
        System.out.println(res);
    }
}

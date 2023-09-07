package 动态规划套路;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/5
 */

//用友一面算法题 凑硬币的升级版
public class 凑硬币II {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] coins = {1,2,5};
        int nums = sc.nextInt(); //一定要用到n枚硬币才行，多了少了都不行。
        //请问有多少种选择方案去拼凑m
        int m = sc.nextInt();
        int res = coinChange(coins,nums,m);
        System.out.println(res);
    }

    private static int coinChange(int[] coins, int nums, int m) {
        if (nums == 0 && m == 0) return 0; //如果m刚好等于0了，或者nums用完了。那就可以结束了
        if (m < 0 || m > 5 * nums) return -1;//如果m<0,或者m> 5*nums 那根本就不可能凑齐。
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subpromble = coinChange(coins,nums - 1,m - coin);
            if (subpromble == -1) continue;
            res = Math.min(res,subpromble + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}

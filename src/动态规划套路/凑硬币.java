package 动态规划套路;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author KingofTetris
 * @File 凑硬币
 * @Time 2021/10/18  19:58
 */
public class 凑硬币 {

    int[] memo;
    @Test
    public void test(){
        int[] coins = {1,5,2};
        int amount = 21;
        System.out.println(coinChange(coins, amount));
    }



    /** 简单的暴力递归，会超时*/
  /*  public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0 ) return -1;
        int res = Integer.MAX_VALUE;
        for(int coin:coins){
            int subpromble = coinChange(coins,amount - coin);
            if (subpromble == -1) continue;//无解就跳过
            // 因为这个continue跳过可能导致 res = Math.min(res,subpromble + 1); 不会执行
            //所有要判断一下res 还是不是MAX_VALUE
            //每次取最少的 记得加一，因为每次会减去一个coin。
            res = Math.min(res,subpromble + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }*/



    //同样可以优化一下重复计算的过程
    /*public int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        Arrays.fill(memo,-666);
        return dp(coins,amount);
    }

    public int dp(int[] coins,int amount){

        if (amount == 0) return 0;
        if (amount < 0) return -1;

        if (memo[amount] != -666)
            return memo[amount];

        int res = Integer.MAX_VALUE;
        for(int coin:coins){
            int subpromble = dp(coins,amount - coin);
            if (subpromble == -1) continue;
            res = Math.min(subpromble + 1,res);
        }

        memo[amount] = (res == Integer.MAX_VALUE)?-1:res;
        return memo[amount];
    }*/


    //最终化成迭代优化版
    int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 数组大小为 amount + 1，初始值也为 amount + 1

       /** PS：为啥 dp 数组初始化为 amount + 1 呢，
        因为凑成 amount 金额的硬币数最多只可能等于 amount（全用 1 元面值的硬币），
        所以初始化为 amount + 1 就相当于初始化为正无穷，便于后续取最小值。
        为啥不直接初始化为 int 型的最大值 Integer.MAX_VALUE 呢？因为后面有 dp[i - coin] + 1，
        这就会导致整型溢出。*/
        Arrays.fill(dp, amount + 1);

        // base case
        dp[0] = 0;
        // 外层 for 循环在遍历所有状态的所有取值
        for (int i = 0; i < dp.length; i++) {
            // 内层 for 循环在求所有选择的最小值
            for (int coin : coins) {
                // 子问题无解，跳过
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }

        //把计算结果放入备忘录
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

}

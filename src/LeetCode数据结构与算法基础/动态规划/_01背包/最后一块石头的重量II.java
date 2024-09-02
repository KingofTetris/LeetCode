package LeetCode数据结构与算法基础.动态规划._01背包;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */
public class 最后一块石头的重量II {

    /**
     * 这道题乍看之下和01背包完全没什么关系
     * 实际上思路在于，我们尽量把这个数组分成2个和相近的子数组
     * 最后他们的差就是我们要返回的值
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2; //尽量分成这样

        //本题目中重要还是一样的 价值和体积等价
        //dp[j] = Math.max(dp[j],dp[j - weight[i]] + value[i])

        //本题也就是 dp[j] = Math.max(dp[j],dp[j - stone[i]] + stone[i])

        //那么问题来了，这个dp数组的长度要设置为多少呢?
        //还是尽量往target去凑
        int[] dp = new int[target + 1];

        for (int i = 0; i < stones.length; i++) {
            //采用倒序
            for (int j = target; j >= stones[i]; j--) {
                //两种情况，要么放，要么不放
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        //因为这个target是由sum/2来的，由于Java语言的特性向下取整
        //那么sum - dp[target] 一定大于等于 dp[target]
        //当然你直接返回两者差的绝对值也是一样
        return (sum - dp[target] )- dp[target];
    }

}

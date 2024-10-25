package LeetCode数据结构与算法基础.贪心;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/9/7
 */
public class 跳跃游戏II {

    //I是问你能不能到达
    //II是问你如果能到达，最少需要多少步。

    /**
     * leetcode上保证了生成的测试用例一定可以到达 nums[n - 1]。
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        //记录跳跃的次数
        int count = 0;
        //当前的覆盖最大区域
        int curDistance = 0;
        //最大的覆盖区域
        int maxDistance = 0;

        //这里的循环条件就有点不一样了，不再是maxRange了。
        for (int i = 0; i < nums.length; i++) {
            //在可覆盖区域内更新最大的覆盖区域
            maxDistance = Math.max(maxDistance, i + nums[i]);
            //说明当前一步，再跳一步就到达了末尾
            if (maxDistance >= nums.length - 1) {
                count++;
                break;
            }
            //走到当前覆盖的最大区域时，更新下一步可达的最大区域
            if (i == curDistance) {
                curDistance = maxDistance;
                count++;
            }
        }
        return count;
    }


    //DP好像简单多了
    public int jumpDP(int[] nums) {
        //dp的含义就是到大i最少需要几步
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        //dp表示跳到i至少有几步
        dp[0] = 0;


        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= nums[i]; j++) {
                if (i + j < nums.length) {
                    /**
                     * 递推公式
                     * 如果能从i直接跳到 i + j
                     * 那么dp[i + j] = dp[i] + 1
                     * 因为会有多重跳法
                     * 每次取小的。
                     * 那么dp[i + j] = Math.min(dp[i + j], dp[i] + 1);选一个最小的。
                     */
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }
}

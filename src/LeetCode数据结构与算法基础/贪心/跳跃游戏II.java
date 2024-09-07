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
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < nums.length) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }
}

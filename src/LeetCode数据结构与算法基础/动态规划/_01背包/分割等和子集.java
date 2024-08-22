package LeetCode数据结构与算法基础.动态规划._01背包;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/20
 */

//一道01背包的变形题
public class 分割等和子集 {
    public boolean canPartition(int[] nums) {
       //判断能否把nums分成2个等和子集
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;

        int target = sum / 2;
        //一共有target大小的背包，去装nums这些物品
        //这题特殊的在于物品的体积和价值是等价的
        int[] dp = new int[target + 1];
        dp[0] = 0;


        /**
         * 要注意 背包压缩成一维数组以后，只能先遍历物品再遍历背包。
         * 二维遍历顺序无所谓是因为二维数组的取值从行列
         * 谁先来取都一样。
         * 但一维不行。
         */
        //遍历物品
        for (int i = 0; i < nums.length; i++) {
            //遍历背包
            //从后向前遍历保证每个物品只拿一次
            for (int j = target; j >= nums[i]; j--) {
                //二维情况下
                //dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weight[i]] + value[i])
                //压缩一下，其实dp[j] 就是上一层dp[i-1][j]复制下来
                dp[j]  = Math.max(dp[j],dp[j - nums[i]] + nums[i]);
            }
        }

        //遍历完成后，如果下面的条件成立，那么就存在这种分法
        return dp[target] == target;
    }

    /*作者：力扣官方题解
    链接：https://leetcode.cn/problems/partition-equal-subset-sum/solutions/442320/fen-ge-deng-he-zi-ji-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}

package LeetCode数据结构与算法基础.贪心;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/9/7
 */
public class K次取反后最大化的数组和 {


    public int largestSumAfterKNegations(int[] nums, int k) {
        if (nums.length == 1) return nums[0];
        // 排序：先把负数处理了
        Arrays.sort(nums);

        //负数越小，取反越大。所以从小到大排序
        for (int i = 0; i < nums.length && k > 0; i++) {
            // 贪心点, 通过负转正, 消耗尽可能多的k
            if (nums[i] < 0) {
                nums[i] = -nums[i];
                k--;
            }
        }

        // 退出循环, 如果K没消耗完，说明数组里面以后全部是正数了
        // 那么就需要判断k是奇数还是偶数
        //如果是偶数，那么其实就不用动了
        //如果是奇数就要减去最小的。
        if (k % 2 == 1) {
            Arrays.sort(nums);
            nums[0] = -nums[0];
        }

        //最后算和就行了
        int sum = 0;
        for (int num : nums) { // 计算最大和
            sum += num;
        }
        return sum;
    }
}

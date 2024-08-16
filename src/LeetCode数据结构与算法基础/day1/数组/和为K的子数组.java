package LeetCode数据结构与算法基础.day1.数组;

/**
 * @author KingofTetris
 * @File 和为K的子数组
 * @Time 2021/10/15  10:42
 */
/*560. 和为 K 的子数组
       给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
        示例 1：
        输入：nums = [1,1,1], k = 2
        输出：2
        示例 2：
        //自己加+0 = k 也算连续子数组
        输入：nums = [1,2,3], k = 3
        输出：2
        示例 3：nums = [1,1,1], k = 3
        输出：1
        提示：
        1 <= nums.length <= 2 * 104
        -1000 <= nums[i] <= 1000
        -107 <= k <= 107*/
public class 和为K的子数组 {

    //思路：暴力法，穷举出所有的子数组，比较其和是否和k相等
    //O(n^3)超时。
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int count = 0;
            for (int j = i; j < len; j++) {
                count += nums[j];
                if (count == k) {
                    sum++;
                }
            }
        }
        return sum;
    }

    //巧用前缀和计算区间之和
    //因为题目要求的就是连续子数组为K，前缀和就很好地满足这个目的。
    public int subarraySum2(int[] nums, int k) {
        //计算前缀和
        /**
         *
         */
        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int count = 0;
        for (int left = 0; left < nums.length; left++) {
            for (int right = left; right < nums.length; right++) {
                if (preSum[right + 1] - preSum[left] == k){
                    count++;
                }
            }
        }

        return count;

       /* 作者：liweiwei1419
        链接：https://leetcode.cn/problems/subarray-sum-equals-k/solutions/247577/bao-li-jie-fa-qian-zhui-he-qian-zhui-he-you-hua-ja/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}

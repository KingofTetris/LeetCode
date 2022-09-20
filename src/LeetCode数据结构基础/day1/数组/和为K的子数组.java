package LeetCode数据结构基础.day1.数组;

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
    //O(n^2)超时。
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
}

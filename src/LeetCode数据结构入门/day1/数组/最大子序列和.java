package LeetCode数据结构入门.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 最大子序列和
 * @Time 2021/9/26  23:16
 */

/*53. 最大子序和
        给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。



        示例 1：

        输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
        输出：6
        解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
        示例 2：

        输入：nums = [1]
        输出：1
        示例 3：

        输入：nums = [0]
        输出：0
        示例 4：

        输入：nums = [-1]
        输出：-1
        示例 5：

        输入：nums = [-100000]
        输出：-100000


        提示：

        1 <= nums.length <= 3 * 104
        -105 <= nums[i] <= 105


        进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。*/
public class 最大子序列和 {
    @Test
    public void test(){

    }

    //其实就一句话，前面累加起来还不如自己大 就把前面扔掉，从我开始加
    public int maxSubArray(int[] nums){
        int pre_sum = 0;
        int maxSum = nums[0];
        for(int x : nums){
            //pre_sum是为了舍弃掉不要的最大和
            pre_sum = Math.max(pre_sum + x,x);

            //maxSum是为了记录上一个最大和
            maxSum = Math.max(maxSum,pre_sum);
        }
        return maxSum;
    }
}

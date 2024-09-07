package LeetCode数据结构与算法基础.贪心;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/9/7
 */
public class 最大子数组和 {

    @Test
    public void test(){
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int maxSubArray = maxSubArray(nums);
        System.out.println(maxSubArray);
    }

    /**
     * 贪心写法
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int sum = Integer.MIN_VALUE;
        int count = 0;

        //只取最大和，如果count <= 0了，那么就把前面的全部抛弃，重新算就可以了。
        for (int num : nums) {
            count += num;
            sum = Math.max(sum, count); // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            if (count <= 0) {
                count = 0; // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            }
        }
        return sum;
    }
}

package 剑指offer第二版.动态规划;

/**
 * @Author KingofTetris
 * @Date 2022/8/31 14:00
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 * 示例1:
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer42_连续子数组的最大和 {

    /**
     * 暴力法1  O(n^3)
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        int max = Integer.MIN_VALUE;
        for(int i = 0;i < nums.length;i++){
            for(int j = i;j < nums.length;j++){
                // 计算sum(i,j) 表示从nums[i]到nums[j]之和
                int sum = 0;
                for(int k = i;k < j;k++)
                    sum += nums[k];
                if(sum > max)
                    max = sum;
            }
        }
        return max;
    }

    /**
     * 暴力法2 O(n^2)
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
            int max = Integer.MIN_VALUE;
            for(int i = 0;i < nums.length;i++){
                int sum = 0; //每轮重置为0
                for(int j = i;j < nums.length;j++){
                    //sum(i,j)=sum(i,j-1)+nums[j]
                    sum += nums[j];
                    if(sum > max)
                        max = sum;
                }
            }
            return max;
    }


    /**
     * 动态规划
     * 设动态规划列表 dp ，dp[i] 代表以元素 nums[i] 为结尾的连续子数组最大和。
     * 则
     * dp[i] =  dp[i - 1] + nums[i],dp[i - 1] > 0  ①
     * dp[i] =  nums[i],dp[i - 1] <= 0; ②
     * 所以dp[i] = Math.max(①，②)
     * @param nums
     * @return
     */
    public int maxSubArray3(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i],nums[i]);
            res = Math.max(res,dp[i]);
        }
        return res;
    }

}

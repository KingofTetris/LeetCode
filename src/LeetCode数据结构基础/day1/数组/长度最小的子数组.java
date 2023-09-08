package LeetCode数据结构基础.day1.数组;

/**
 * @author by KingOfTetris
 * @date 2023/9/8
 */

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，
 * 找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 *
 * 示例：
 *
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 提示：
 *
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class 长度最小的子数组 {
    public static void main(String[] args) {
//        int[] nums = {1,1,1,1,1,1,1,1};
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7, nums));
    }

    //10^5 一平方就是 10^10 暴力是过不去的，
    //这题就是明显的滑动窗口的题目
    public static int minSubArrayLen(int target, int[] nums) {
        //nums = [2,3,1,2,4,3]
        //两个指针left,right right先动
        //如果sum之和一直小于target right继续动
        //如果sum之和大于等于target 那么right停下left动 [2,3,1,2]
        //也就变成了[3,1,2] 还是>=7
        //left继续动变成 [1,2] 这个时候< 7 left继续动
        //重复这个过程直到right再加一就大于数组长度就结束。
        int left = 0,right = 0;
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        int sum = nums[0];//初值为nums[0]
        while(right < n){
            //没有必要重复去计算sum
           /* for (int i = left; i <= right; i++) {
                sum += nums[i];
            }*/
            if (sum >= target){
                res = Math.min(res,right - left + 1);
                //sum先减去left再左移
                sum -= nums[left];
                left++;
            }else {
                //right右移，sum加上right
                right++;
                //先移动就需要判断边界。
                if (right < n)
                sum += nums[right];//指针右移sum+right
            }
        }
        //如果无解返回0，不是-1.
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

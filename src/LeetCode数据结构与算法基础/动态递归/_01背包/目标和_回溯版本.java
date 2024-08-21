package LeetCode数据结构与算法基础.动态递归._01背包;

class 目标和_回溯版本 {

    //暴力回溯
     int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int startIndex, int sum) {
        if (startIndex == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            //要么加上nums[i] 要么减去nums[i]
            backtrack(nums, target, startIndex + 1, sum + nums[startIndex]);
            backtrack(nums, target, startIndex + 1, sum - nums[startIndex]);
        }
    }

}

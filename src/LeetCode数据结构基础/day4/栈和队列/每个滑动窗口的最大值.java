package LeetCode数据结构基础.day4.栈和队列;

/**
 * @author by KingOfTetris
 * @date 2023/9/13
 */
public class 每个滑动窗口的最大值 {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(maxWindow(nums, 3));
    }
    public static int maxWindow(int[] nums,int k){
        int left = 1;
        int right = k;
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < k; i++) {
            max += nums[i];
        }
        int sumNow = max;
        while (right < n){
            //sumNow 减去left 加上right
            sumNow += (-nums[left - 1] + nums[right]);
            max = Math.max(max,sumNow);
            left++;
            right++;
        }
        return max;
    }
}

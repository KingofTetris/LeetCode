package LeetCode数据结构与算法基础.动态规划;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/11/3
 */


//找到数组中最长的乘积为正数的子数组的长度。
public class 乘积为正数的最长连续子数组 {

    @Test
    public void test(){
        ArrayList<Integer> nums = new ArrayList<>();
        int[] numArr = {-1,2,3,-1,-1,4};
        for (int i : numArr) {
            nums.add(i);
        }
        int longestSubArray = findLongestSubArray(nums);
        System.out.println(longestSubArray);
    }

    //90%?
    public int findLongestSubArray (ArrayList<Integer> nums) {
        // write code here
        int n = nums.size();
        int[] numsArr = new int[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums.get(i);
        }
        //dp
        boolean[] dp = new boolean[n];
        dp[0] = numsArr[0] > 0;//如果dp[0]大于0，记为true
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] && numsArr[i] > 0){
                dp[i] = true;
            }else if (!dp[i - 1] && numsArr[i] < 0){
                dp[i] = true;
            }else {
                dp[i] = false;
            }
        }
        System.out.println(Arrays.toString(dp));
        //然后下标为true最大的那个下标
        int index = -1;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i]){
                index = i;
            }
        }
        return index + 1;
    }
}

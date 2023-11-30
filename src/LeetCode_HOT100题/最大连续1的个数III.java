package LeetCode_HOT100题;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/11/19
 */
public class 最大连续1的个数III {

    @Test
    public void test(){
        int[] nums = {1,1,0,1,1,0,1,1,1,1,0,0};
        int i = maxOne(nums, 1);
        System.out.println(i);
    }

    //滑动窗口
    public int maxOne(int[] nums,int k){
        int n = nums.length;
        int l = 0;
        int r = 0;
        int count = 0;
        while (r < n) {
            if (nums[r] == 0) count++;
            if (count > k) {
                if (nums[l] == 0) count--;
                l++;
            }
            r++;
        }
        return r - l;
    }
}

package 每日一题;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/10/7 9:31
 */
public class 最大升序子数组和_2022_10_07 {

    @Test
    public void test(){
        int[] sums = {9,5,3,2,10};
        int i = maxAscendingSum(sums);
        System.out.println(i);
    }

    /**
     * 双指针一次遍历
     * @param nums
     * @return
     */
    public int maxAscendingSum(int[] nums) {

        if (nums.length == 1) return nums[0];

        int i = 0,j = 1;

        int sum = 0,res = 0;

        while (j < nums.length){
            sum = nums[i];
            while (j < nums.length && nums[i] < nums[j]){
                sum += nums[j];
                i++;
                j++;
            }
            res = Math.max(res,sum);
            i++;//i这个时候i指向大的，往后走一步到小的，重新开始下一轮升序
            j++;
        }

        return res;
    }
}

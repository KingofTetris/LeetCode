package LeetCode数据结构与算法基础.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 递增的三元子序列
 * @Time 2021/10/14  20:19
 */

/*334. 递增的三元子序列
        给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。

        如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，
        返回 true ；否则，返回 false 。



        示例 1：

        输入：nums = [1,2,3,4,5]
        输出：true
        解释：任何 i < j < k 的三元组都满足题意
        示例 2：

        输入：nums = [5,4,3,2,1]
        输出：false
        解释：不存在满足题意的三元组
        示例 3：

        输入：nums = [2,1,5,0,4,6]
        输出：true
        解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6


        提示：

        1 <= nums.length <= 105
        -231 <= nums[i] <= 231 - 1*/
public class 递增的三元子序列 {

    @Test
    public void test(){
        int[] test = {3,0,4,1,3,4,5};
        System.out.println(increasingTriplet(test));
    }


 /*   对于要寻找的三元组 (min, mid, max)，持续记录 min 和 mid 的值并根据当前遍历到的值 i 进行更新

    当 i 的值：

    i > mid： 已找到满足条件的三元组

    i <=min： 更新 min = i 以便后面组成新的三元组。mid 不做更改因为后面还有可能出现 j > mid
    可以使 (min,mid,j) 满足条件，但是因为 mid > min，所以可以只保留 mid 来做后面的比较

    i > min and i <= mid： 更新 mid = i，如果后面有满足当前三元组条件的数字 j，即 mid < j，
    那么 i < j 也必定满足，且比 i 大的数比 mid 的多，所以 i 作为新的 mid值为更优解*/


    public boolean increasingTriplet(int[] nums) {
        if(nums.length < 3) return false;
        int min = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
//        {3,0,4,1,3,4,5};
        //实际上找到了0，1，3
        for(int i : nums){
            if(i > mid) return true; //找到比min,mid都大的数就组成了一个三元组
            if(i <= min) min = i;
            else mid = i;
        }
        return false;

    }
}

package LeetCode数据结构与算法基础.手撕算法;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author by KingOfTetris
 * @date 2024/10/24
 */
public class 存在重复元素 {

    public boolean containsDuplicate(int[] nums) {
        //不用HashMap，排个序，相邻的相等就存在重复元素
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                return true;
            }
        }
        return false;
    }


    //加个set
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n: nums) {
            if (!set.add(n)) {
                return true;
            }
        }
        return false;
    }
}

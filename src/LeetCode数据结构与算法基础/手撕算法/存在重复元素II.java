package LeetCode数据结构与算法基础.手撕算法;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author by KingOfTetris
 * @date 2024/10/26
 */
public class 存在重复元素II {
    //暴力比较。O(n*k) O(1)
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int cover = i + k;
            if (cover > nums.length - 1) cover = nums.length - 1;
            for (int j = i + 1; j <= cover; j++) {
                if (nums[j] == nums[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    //HashMap
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        //map记录K-V，值和对应的索引位置。
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            else {
                map.put(nums[i], i);
            }
        }
        return false;
    }
}

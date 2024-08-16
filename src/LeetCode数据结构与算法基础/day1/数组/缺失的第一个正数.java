package LeetCode数据结构与算法基础.day1.数组;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author by KingOfTetris
 * @date 2024/8/15
 */
public class 缺失的第一个正数 {


    //最简单的方法是使用一个hash表存储数字1-N+1是否出现过
    //第一个没出现的正数就是要找的那个数 O(N) O(N)
    public int firstMissingPositive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }
        int missing = 1;
        while (set.contains(missing)) {
            missing++;
        }
        return missing;
    }
    //也可以先排序再二分查找，O(NlogN) O(1)

    //最后下面是本题目要求的解法，O(N) O(1)


    /**
     * https://www.bilibili.com/video/BV167411N7vd/?spm_id_from=333.337.search-card.all.click&vd_source=299caa32bd4dc5f5ad17129611289250
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int N = nums.length;
        for (int i = 0; i < nums.length; i++) {
            //思路就是把i+1 放在下标 i上。
            while (nums[i] >= 1 && nums[i] <= N && nums[nums[i] -1 ] != nums[i]){
                swap(nums,i,nums[i] - 1);
            }
        }
        //交换完毕以后再从左往右遍历
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return N + 1;
    }

    public void swap(int[] nums,int i ,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

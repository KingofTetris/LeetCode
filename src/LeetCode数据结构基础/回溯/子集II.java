package LeetCode数据结构基础.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/10/23
 */
public class 子集II {


    @Test
    public void test(){
        int[] nums = {1,2,2};
        List<List<Integer>> lists = subsetsWithDup(nums);
        System.out.println(lists);
    }

    /**
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     * 示例 2：
     *
     * 输入：nums = [0]
     * 输出：[[],[0]]
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> subset = new ArrayList<>();
    boolean[] used;//去重数组
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);//去重先排序，让相邻元素挨在一起。
        used = new boolean[nums.length];
        backTracking(nums,0);
        return result;
    }
    private void backTracking(int[] nums, int startIndex) {
        result.add(new ArrayList<>(subset));
        if (startIndex >= nums.length){
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            //树层去重的关键
            // i > 0 && nums[i] == nums[i-1] && !used[i-1]
            //注意去重前，一定要先排序。
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]){
                continue;
            }
            subset.add(nums[i]);
            used[i] = true;
            backTracking(nums, i + 1);
            subset.remove(subset.size() - 1);
            used[i] = false;
        }
    }
}

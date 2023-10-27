package LeetCode数据结构基础.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/10/23
 */
public class 子集 {

    @Test
    public void test(){
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
    }

    List<List<Integer>> res = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backTracking(nums,0);
        return res;
    }


    //求子集是不用剪枝的，因为你必须遍历完整棵树才知道结果。
    private void backTracking(int[] nums, int startIndex) {
        res.add(new ArrayList<>(path));//把当前path添加到结果中。
        //终止条件,其实这道题可以不加
        if (startIndex >= nums.length){
            return;
        }
        for (int i = startIndex;i < nums.length; i++) {
            path.add(nums[i]);
            //i+1 不能重复使用.
            backTracking(nums,i + 1);
            path.remove(path.size() - 1);
        }
    }

}

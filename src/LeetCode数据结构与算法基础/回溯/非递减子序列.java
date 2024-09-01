package LeetCode数据结构与算法基础.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/10/23
 */

/**
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中
 * 至少有两个元素 。你可以按 任意顺序 返回答案。
 *
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,6,7,7]
 * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * 示例 2：
 *
 * 输入：nums = [4,4,3,2,1]
 * 输出：[[4,4]]
 */
public class 非递减子序列 {

    @Test
    public void test(){
        int[] nums = {4,6,7,7};
        List<List<Integer>> subsequences = findSubsequences(nums);
        System.out.println(subsequences);
    }
    /**
     * 递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
     * 示例 1：
     *
     * 输入：nums = [4,7,6,7]
     * 输出：[[4,6],[4,6,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
     */

    /**
     * 这题比较恶心的是不能先排序改变相对位置。就不能像之前那么直接用used数组
     * 比如[4,7,6,7] 如果排序以后就变成了 [4,6,7,7]
     * 那么结果就多了一个[4,6,7,7]。因为6和7的相对位置变了。
     *
     * 如果这个数组够长的话，那么结果就会有非常多不应该存在的递增子序列。
     * 所以这题不能直接Arrays.sort，然后用used去重。
     * 这题得使用set来去重。
     * @param nums
     * @return
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
//    HashSet<Integer> set = new HashSet<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        backTracking(nums, 0);
        return res;
    }

    private void backTracking(int[] nums, int startIndex) {
        //终止条件
        //子集问题，先加入集合,不要return.
        if (path.size() >= 2) {
            res.add(new ArrayList<>(path));
        }
        //子集当startIndex >= n 才return
        if (startIndex >= nums.length) {
            return;
        }
        //用set来记录当前树层是否已经取过相同大小的元素
        //注意是每层新建一个set，就不需要还原set了，每层都是新的
        /** 从这题就可以看出来，比起used数组，
         * 这个Set其实是更通用的去重做法，
         * 但是使用set去重的版本相对于used数组的版本效率都要低很多
         *
         * **/
        HashSet<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            //1.如果nums[i] 小于 path最后一个元素，那么就不是非递减子序列
            //2.nums[i]在树层上已经取过了，就不要再重复取了。
            if (path.size() >= 1 && nums[i] < path.get(path.size() - 1)) continue;
            if (set.contains(nums[i])) continue;
            //记录重复元素
            set.add(nums[i]);
            path.add(nums[i]);
            //下一层递归
            backTracking(nums,i + 1);
            path.remove(path.size() - 1);
        }
    }


}

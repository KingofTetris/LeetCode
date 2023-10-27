package LeetCode数据结构基础.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/10/23
 */
public class 递增子序列 {

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
     * 输入：nums = [4,6,7,7]
     * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
     */

    /**
     * 这题比较恶心的是不能先排序改变相对位置。就不能像之前那么直接用used数组
     *
     * @param nums
     * @return
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

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
        if (startIndex >= nums.length) {
            return;
        }
        //用set来记录当前树层是否已经取过相同大小的元素
        //树枝是不影响的，所以最后递归的时候不用还原set
        HashSet<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            //1.如果nums[i] 小于 path最后一个元素，那么就不是递增子序列
            //2.nums[i]在树层上已经取过了，就不要再重复取了。
            if (path.size() >= 1 && nums[i] < path.get(path.size() - 1)
                    || set.contains(nums[i])) {
                continue;
            }
            //记录重复元素
            set.add(nums[i]);
            path.add(nums[i]);
            //下一层递归
            backTracking(nums,i + 1);
            path.remove(path.size() - 1);
        }
    }


}

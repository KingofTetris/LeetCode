package LeetCode数据结构与算法基础.回溯;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/9/11
 */


/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
 *
 * 排列对比组合，数字的顺序就有区分了
 * [1,2]和[2,1]就是不同的排列。但是在组合里面就属于重复了。
 */
public class 全排列 {

    @Test
    public void test(){
        int[] nums = {1,2,3,4};
        permute(nums);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }

    List<List<Integer>> res = new LinkedList<>();
    List<Integer> path = new LinkedList<>();
    //这个used数组的作用和前面的回溯也是差不多的。
    //如果一个元素已经取过了，我们把他标记为true，避免重复多次使用。
    //排列也需要这个used数组来区分顺序，以防重复。
    boolean[] used;
    //全排列顺序是有区别的。不能像组合那样不区分。
    public List<List<Integer>> permute(int[] nums){
        if (nums.length == 0 ) return res;
        used = new boolean[nums.length];
        //要注意排列就不能再使用startIndex了
        //startIndex是为了避免元素重复使用，导致得到相同的组合
        //而排列由于顺序的不同，是可以重复使用的。
        backtracking(nums);
        return res;
    }

    private void backtracking(int[] nums) {
        //终止条件是什么?
        if (path.size() == nums.length){
            res.add(new LinkedList<>(path));
            return;
        }
        //这里是i=0 不是startIndex了！  排列和组合的差别就在这。
        //我们用used标记我们用过了什么元素。
        /**
         * 注意这里不是startIndex，是0，排列每个元素都可以重复使用
         * 只是你前面用过了后面就别再用了，所以需要个used数组
         */
        for (int i = 0; i < nums.length; i++) {
            //排列是区分顺序的，所以不需要在树层上去重
            //因为比如说[1,2,1] 和 [1,1,2]是不一样的排列
            //前面的排列是包含不到后面的排列的

            //这个used数组只是树枝标记。标记树枝上哪些元素用过了。
            //用过就别再用了。
            if (used[i] == true) continue;
            path.add(nums[i]);
            used[i] = true;
            backtracking(nums);
            //回溯 把条件重置。
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }


}

package LeetCode算法20天.回溯;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/9/11
 */
public class 全排列 {

    @Test
    public void test(){
        int[] nums = {1,2,3};
        permute(nums);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }

    List<List<Integer>> res = new LinkedList<>();
    List<Integer> path = new LinkedList<>();
    //这个used数组的作用和前面的回溯也是差不多的。
    //如果一个元素以及取过了，我们把他标记为1，避免重复多次使用。
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

        //这里是i=0 不是startIndex了！
        //排列和组合的差别就在这。
        //我们用used标记我们用过了什么元素。
        for (int i = 0; i < nums.length; i++) {
            //多了个标记数组，就要对这个标记数组加上对应的操作。
            if (used[i]) continue;
            path.add(nums[i]);
            used[i] = true;
            backtracking(nums);
            //回溯 把条件重置。
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }


}

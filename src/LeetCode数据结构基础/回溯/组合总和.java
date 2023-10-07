package LeetCode数据结构基础.回溯;

/**
 * @author by KingOfTetris
 * @date 2023/9/9
 */

import java.util.*;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为： [ [7], [2,2,3] ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为： [ [2,2,2,2], [2,3,3], [3,5] ]
 */
public class 组合总和 {

    public static void main(String[] args) {
        组合总和 s = new 组合总和();
        int[] candidates = {8,7,4,3};
        int target = 11;
        List<List<Integer>> res = s.comineNum(candidates, target);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }

    List<List<Integer>> res = new LinkedList<>();
    List<Integer> path = new LinkedList<>();
    public List<List<Integer>> comineNum(int[] candidates, int target){
        //先排序，方便剪枝。
        Arrays.sort(candidates);
        backtracking(candidates,target,0,0);
        return res;
    }

    //他可以重复使用，不需要start.
    //但是问题出现了，没有了start，变成了排列数。
    //但是他本质还是要用组合来看待，那么
    //这个startIndex就最大作用就是去重求组和，如果你要排列，就没必要加上startIndex了
    private void backtracking(int[] candidates, int target,int sum,int startIndex) {
      /*  //终止条件
        if (sum > target){
            return;
        }*/
        if (sum == target){
            res.add(new LinkedList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (sum + candidates[i] > target) break;//当前sum加上candidate[i]已经大于target剪枝。
            path.add(candidates[i]);
            //这里的start要特别小心！！！因为这题是可重复使用的
            //所以start还是i 而不是i + 1
            backtracking(candidates,target,sum + candidates[i],i);
            path.remove(path.size() - 1);
        }
    }
}

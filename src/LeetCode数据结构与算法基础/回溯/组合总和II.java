package LeetCode数据结构与算法基础.回溯;

/**
 * @author by KingOfTetris
 * @date 2023/9/9
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明： 所有数字（包括目标数）都是正整数。解集不能包含重复的组合。
 * <p>
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 */

//这题和1的差别是不能重复选，而且数组里面是有重复元素。
//只是下标不同。
//这就需要一个访问数组used，来去重
public class 组合总和II {

    /**
     * 结果里面有两组一样的。其实是不一样的，因为我们使用的1是不同的1，2也是不同的2.确实没有重复使用。
     * 只是两个数字都是一样的而已。
     * 但是在内容上相同，我们还是要去重。而且是树层去重
     * [1, 2, 5]
     * [1, 7]
     * [1, 2, 5]
     * [1, 7]
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        组合总和II s = new 组合总和II();
        List<List<Integer>> res = s.combinationSum2(candidates, target);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }

    List<Integer> path = new LinkedList<>();
    List<List<Integer>> ans = new ArrayList<>();
    //树层去重，这个used数组很重要。
    boolean[] used;
    int sum = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 加标志数组，用来辅助判断同层节点是否已经遍历
        used = new boolean[candidates.length];
        // 为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);
        backTracking(candidates, target, 0);
        return ans;
    }

    private void backTracking(int[] candidates, int target, int startIndex) {
        //终止条件
        if (sum == target) {
            ans.add(new ArrayList(path));
        }
        for (int i = startIndex; i < candidates.length; i++) {
            //sum + 当前元素已经大于target 剪枝。不用再继续dfs了
            if (sum + candidates[i] > target) {
                break;
            }
            // 树层去重逻辑，本题的关键。前提是已经给数组排序了
            // candidates[i] == candidates[i - 1]就说明前一个相同大小的元素已经DFS过一遍了
            // 后面的相同大小元素就没有必要再DFS一次了
            // 为了防止i - 1 < 0所以才需要i>0
            //最后的used[i-1] == false
            //是因为我们每次回溯以后会把used改为false
            //所以需要前面used为false;留下树枝的结果。
            //used[i - 1] == false 才能确定树层的前一个相同元素已经使用过了。
            //保证树层去重，树枝上是可以重复的
            //如果used[i -1]==true 那说明是树枝的状态，树枝是可以重复的
            if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == false) {
                continue;
            }
            used[i] = true;
            sum += candidates[i];
            path.add(candidates[i]);
            // 每个节点仅能选择一次，所以从下一位开始
            backTracking(candidates, target, i + 1);
            used[i] = false;
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }
}

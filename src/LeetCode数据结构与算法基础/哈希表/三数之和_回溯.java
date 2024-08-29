package LeetCode数据结构与算法基础.哈希表;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/8/27
 */
public class 三数之和_回溯 {

    @Test
    public void test() {
        int[] nums = {-2, 2, -2, 2, 0};
        List<List<Integer>> res = threeSum(nums, 0);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }

    //去重套路，used数组+排序
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> threeSum(int[] nums, int target) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backTracking(nums, target, 0);
        return res;
    }

    public void backTracking(int[] nums, int target, int startIndex) {
        //停止条件
        if (path.size() == 3 && target == 0) {
            res.add(new LinkedList<>(path));
            return;
        }

        //单层回溯
        //求组合
        for (int i = startIndex; i < nums.length; i++) {
            //去重判断 如果前后两个数相等，并且i-1已经用过了，就没必要再走nums[i]了
            //used[i-1] == false 但是后一个数与前一个数一致，说明已经是回溯了，这个分支没必要再走了。
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]){
                continue;
            }

            used[i] = true;
            path.add(nums[i]);
            backTracking(nums, target - nums[i], i + 1);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}

package LeetCode数据结构与算法基础.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/9/11
 */

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2]
 * 输出： [[1,1,2], [1,2,1], [2,1,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */


//这题和1的区别是 数组里面出现了重复的元素。
public class 全排列II_used数组 {
    @Test
    public void test() {
        int[] nums = {3,3,0,3};
        //再用老一套used数组，你会发现没有做到去重。
        //因为他确实是用到了不同的下标，但是这些下标的元素相同。
        permuteUnique(nums);
        for (List<Integer> re : result) {
            System.out.println(re);
        }
    }

    //存放结果
    List<List<Integer>> result = new ArrayList<>();
    //暂存结果
    List<Integer> path = new ArrayList<>();
    boolean[] used ;
    public List<List<Integer>> permuteUnique(int[] nums) {
        //去重前排序，去重前排序，去重前排序！！！说了TM一万遍了，你怎么记不住？？
        //这个排序非常关键！
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backTrack(nums);
        return result;
    }

    private void backTrack(int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过，
            // 如果同⼀树层nums[i - 1]使⽤过，那么一定会发生重复，就直接跳过
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            //如果仅仅是同⼀树⽀，那么nums[i]是可以重复使用的。
            if (!used[i]) {
                used[i] = true;//标记同⼀树⽀nums[i]使⽤过，防止同一树枝重复使用
                path.add(nums[i]);
                backTrack(nums);
                path.remove(path.size() - 1);//回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
                used[i] = false;//回溯
            }
        }
    }

}

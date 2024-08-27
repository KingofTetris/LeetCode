package LeetCode数据结构与算法基础.哈希表;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2022/10/14
 * <p>
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] 
 * （若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 四数之和 {

    @Test
    public void test() {
        //暴力回溯，下面这个结果还要去重
        //这个和组合总和II一样，树层重复，虽然下标不同，但是内容一样，导致的重复。
        //所以为了解决重复，就需要来个树层访问数组
        int[] nums = new int[]{2,-2,-2,2,2};
//        int nums[] = new int[]{1000000000, 1000000000, 1000000000, 1000000000};
        List<List<Integer>> lists = fourSum2(nums, 0);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
//        System.out.println();//-294967296
    }

    /**
     * 所有基本测试用例都搞定以后
     * 还要考虑溢出的问题
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        //要去重的数组，一般都需要先给他排序。
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // nums[i] > target 直接返回, 剪枝操作
            if (nums[i] > 0 && nums[i] > target) {
                return result;
            }
            if (i > 0 && nums[i - 1] == nums[i]) {    // 对nums[i]去重
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {  // 对nums[j]去重
                    continue;
                }
                //固定前两个数i和j
                //现在去找后面两个
                int left = j + 1;
                int right = nums.length - 1;
                while (right > left) {
                    // nums[k] + nums[i] + nums[left] + nums[right] > target int会溢出
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 对nums[left]和nums[right]去重
                        while (right > left && nums[right] == nums[right - 1]) right--;
                        while (right > left && nums[left] == nums[left + 1]) left++;
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }


    /**
     * 下面是暴力回溯法，超时。
     * 想想从哪剪枝？
     * 因为排序了以后，前面的数组如果已经超过target
     * 在这个函数里面也就是
     * 如果target < 0
     * 那么就不可能了。
     */
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> path = new LinkedList<>();
    boolean[] used;
    public List<List<Integer>> fourSum2(int[] nums, int target){
        used = new boolean[nums.length];
        Arrays.sort(nums); //为了去重，往往要先把内容相同的元素放到一起，使他们相邻。
        backtracking(nums,target,0);
        return res;
    }

    //不能重复，
    private void backtracking(int[] nums, int target, int startIndex) {
        //终止条件
        if (path.size() == 4 && target == 0){
            res.add(new LinkedList<>(path));
            return;
        }
        int n = nums.length;
        for (int i = startIndex; i < n; i++) {
            // 出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            //树层标记为true 表示已经访问过。
            used[i] = true;
            path.add(nums[i]);
            backtracking(nums,target - nums[i],i + 1);
            path.remove(path.size() - 1);
            //回溯后记得重新标为false
            used[i] = false;
        }
    }
}

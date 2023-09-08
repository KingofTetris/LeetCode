package LeetCode数据结构基础.day1.数组;

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
//        int nums[] = new int[]{1,0,-1,0,-2,2};
//        int nums[] = new int[]{2,2,2,2,2};
//        int nums[] = new int[]{-2,-1,-1,1,1,2,2};
//        int nums[] = new int[]{-1,0,1,2,-1,-4};
//        int nums[] = new int[]{1,-2,-5,-4,-3,3,3,5};
//        int nums[] = new int[]{-1,-5,-5,-3,2,5,0,4};
        int nums[] = new int[]{1000000000, 1000000000, 1000000000, 1000000000};
        List<List<Integer>> lists = fourSum(nums, -294967296);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }

//        System.out.println();//-294967296
    }

    /**
     * 所有基本测试用例都搞定以后
     * 还要考虑溢出的问题
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
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
}

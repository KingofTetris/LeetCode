package LeetCode数据结构与算法基础.day1.数组;

/**
 * @author by KingOfTetris
 * @date 2024/10/31
 */

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * lc163
 * <p>
 * 给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。样例
 * <p>
 * 示例：
 * <p>
 * 输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
 * 输出: [“2”, “4->49”, “51->74”, “76->99”]
 */
public class 缺失的区间 {

    @Test
    public void test(){
        int[] nums = {0, 1, 3, 50, 75};
        List<String> missingRanges = findMissingRanges(nums, 0, 99);
        System.out.println(missingRanges);
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new LinkedList<>();
        // 初始化prev为lower-1，判断是否存在“第一个”区间
        int prev = lower - 1, curr = 0;
        for (int i = 0; i <= nums.length; i++) {
            //当遍历到length时，设置curr为upper+1，判断是否存在“最后一个”区间
            curr = i == nums.length ? upper + 1 : nums[i];
            //如果上一个数字和当前数字相差大于1 说明之间存在区间
            if (curr - prev > 1) {
                //1 3  [2,2]
                //1 5  [2,4]
                res.add(getRange(prev + 1, curr - 1));
            }
            prev = curr;
        }
        return res;
    }

    private String getRange(int from, int to) {
        return from == to ? String.valueOf(from) : from + "->" + to;
    }


}

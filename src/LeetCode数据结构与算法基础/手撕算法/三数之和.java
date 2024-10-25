package LeetCode数据结构与算法基础.手撕算法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/10/24
 */
public class 三数之和 {

    //这题回溯超时，只能用哈希表 + 二分
    public List<List<Integer>> threeSum(int[] nums) {// 总时间复杂度：O(n^2)
        List<List<Integer>> ans = new ArrayList<>();
        //小于3个数直接返回列表就行了
        if (nums.length <= 2) return ans;
        Arrays.sort(nums); // 先排序，O(nlogn)
        for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
            if (nums[i] > 0) break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
            //无重复的三元组，如果nums[i]和前一个一样就跳过
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去掉重复情况
            //以这个数的相反数为目标，相加为0 就是找它的相反数
            int target = -nums[i];
            //left为当前固定nums[i]的下一个数的下标，right为末尾下标
            int left = i + 1, right = nums.length - 1;
//            终止条件left >= right
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    ArrayList<Integer> res = new ArrayList<>();
                    res.add(nums[i]);
                    res.add(nums[left]);
                    res.add(nums[right]);
                    ans.add(res);
                    //现在是找到了一组三数之和等于=0的情况
                    //那么必然要增加 left，减小 right，
                    // 找下一组,但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3],
                    // nums[i] = -2, left = 1, right = 6,
                    // [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    left++; right--; // 首先去掉当前这一组。
                    //左边相等left++ 右边相等right-- 去重
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
                //两数相加小于target 就让left++ 找大一点的数和最大数的相加 left++
                else if (nums[left] + nums[right] < target) {
                    left++;
                }
                //反过来大于target 就拿小一点的数和最小数相加 right--
                else {  // nums[left] + nums[right] > target
                    right--;
                }
            }
        }
        return ans;
    }
}

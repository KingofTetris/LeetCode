package LeetCode数据结构与算法基础.双指针与滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 删除子数组的最大得分 {

    public int maximumUniqueSubarray(int[] nums) {
        int ans = 0;
        int sum = 0;
        int l = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int r = 0; r < nums.length; r++) {
            while (map.containsKey(nums[r])) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }
                sum -= nums[l];
                l++;
            }
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            sum += nums[r];
            ans = Math.max(ans, sum);
        }
        return ans;
    }

   /* 作者：yusir
    链接：https://leetcode.cn/problems/maximum-erasure-value/solutions/2794687/hua-dong-chuang-kou-javapythonti-jie-by-ksrd8/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}

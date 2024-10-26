package LeetCode数据结构与算法基础.双指针与滑动窗口;

import java.util.TreeSet;

/**
 * @author by KingOfTetris
 * @date 2024/10/26
 */
public class 存在重复元素III {
    //找了个下标i,j abs(i-j) <= indexDiff,abs(nums[i] - nums[j]) < valueDiff
    //找得到返回true，找不到返回false

    //暴力法不用试了，我试过了。超时。
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) valueDiff);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) valueDiff) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= indexDiff) {
                set.remove((long) nums[i - indexDiff]);
            }
        }
        return false;

      /*  作者：力扣官方题解
        链接：https://leetcode.cn/problems/contains-duplicate-iii/solutions/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}

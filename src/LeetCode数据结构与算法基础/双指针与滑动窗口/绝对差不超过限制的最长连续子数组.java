package LeetCode数据结构与算法基础.双指针与滑动窗口;

import java.util.TreeMap;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 绝对差不超过限制的最长连续子数组 {

    //方法一：滑动窗口 + 有序集合

    /**
     * 我们可以枚举每一个位置作为右端点，找到其对应的最靠左的左端点，满足区间中最大值与最小值的差不超过 limit。
     *
     * 注意到随着右端点向右移动，左端点也将向右移动，于是我们可以使用滑动窗口解决本题。
     *
     * 为了方便统计当前窗口内的最大值与最小值，我们可以使用平衡树：
     *
     * 语言自带的红黑树，例如 C++ 中的 std::multiset，Java 中的 TreeMap；
     *
     * 第三方的平衡树库，例如 Python 中的 sortedcontainers（事实上，这个库的底层实现并不是平衡树，但各种操作的时间复杂度仍然很优秀）；
     *
     * 手写 Treap 一类的平衡树，例如下面的 Golang 代码。
     *
     * 来维护窗口内元素构成的有序集合。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solutions/612688/jue-dui-chai-bu-chao-guo-xian-zhi-de-zui-5bki/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = nums.length;
        int left = 0, right = 0;
        int ret = 0;
        while (right < n) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }

    /*作者：力扣官方题解
    链接：https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solutions/612688/jue-dui-chai-bu-chao-guo-xian-zhi-de-zui-5bki/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}

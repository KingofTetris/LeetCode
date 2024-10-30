package LeetCode数据结构与算法基础.二分查找专题;

public class 寻找峰值 {

    //题目很简单
    //给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
    //并且你可以认为nums[-1] = nums[n] = -∞
    //最简单的做法就是返回最大值的下标即可
    public int findPeakElement(int[] nums) {
        int idx = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[idx]) {
                idx = i;
            }
        }
        return idx;
    }

    //但是有要求你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
    //这就需要二分查找了。
    public int findPeakElement2(int[] nums) {
        //因为题目还保证了对于所有有效的 i 都有 nums[i] != nums[i + 1] 也就是不会存在 1,1,1,1 这种全部一样的情况
        //那么mid 的一侧一定存在峰值。
        //我们选择右边。
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;

    /*    作者：画手大鹏
        链接：https://leetcode.cn/problems/find-peak-element/solutions/6695/hua-jie-suan-fa-162-xun-zhao-feng-zhi-by-guanpengc/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}

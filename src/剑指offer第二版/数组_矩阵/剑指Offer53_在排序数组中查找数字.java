package 剑指offer第二版.数组_矩阵;

/**
 * @author by KingOfTetris
 * @date 2022/9/8
 * 统计一个数字在排序数组中出现的次数。
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums 是一个非递减数组
 * -10^9 <= target <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer53_在排序数组中查找数字 {

    /**
     * 遍历
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) count++;
        }
        return count;
    }

    /**
     * 因为已经是排好序的数组，只需要找到目标数字的第一次出现和最后一次出现的下标left right
     * 然后right - left + 1就是这个数的重复次数
     * 简单二分查找
     * 搞这么多事就是为了减少复杂度到O(logN)
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        //因为代码逻辑基本一样，用lower这个flag来区分是找left 还是 right
        int leftIdx = binarySearch(nums, target, true); //第一个大于等于target元素的下标(大于等于是因为target可能不存在)
        int rightIdx = binarySearch(nums, target, false) - 1;//第一个大于target的元素下标减去1就是重复元素的最后一个位置

        /**
         * 因为数组中可能没有target 所以需要加强判断
         */
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return rightIdx - leftIdx + 1;
        }
        return 0;
    }


    /**
     * Lower作为标记
     * 如果 lower 为 true，则查找第一个大于等于 target 的下标，
     * 否则查找第一个大于 target 的下标。
     *
     * 最后，因为 target 可能不存在数组中，
     * 因此我们需要重新校验我们得到的两个下标 leftIdx 和 rightIdx，
     * 看是否符合条件，如果符合条件就返回 right−left+1，不符合就返回 0。
     *
     * @param nums
     * @param target
     * @param lower
     * @return
     */
    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1;
        int ans = nums.length;
        while (left <= right) { //二分的终止条件left > right
            int mid = left + (right - left) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                //找到了target还要继续求mid的原因在于我们要找到第一个target所以要>=
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
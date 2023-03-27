package LeetCode_HOT100题;

/**
 * @author by KingOfTetris
 * @date 2022/10/17
 */
public class 在排序数组中查找元素的第一个和最后一个位置 {


    /**
     * 二分查找优化速度到O(logn)
     *
     * 对于升序序列某元素的开始和结束
     * 我们可以看成找开始位置和下一个比它大的元素的开始位置。就不用去找结束位置
     * 这样某元素的结束位置就是下一个元素的开始位置-1；
     *
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {

        int leftIndex = binarySearch(nums,target,true);
        int rightIndex = binarySearch(nums,target,false) - 1;
        if (leftIndex <= rightIndex && rightIndex < nums.length
                && nums[leftIndex] ==target && nums[rightIndex] == target){
            return new int[]{leftIndex,rightIndex};
        }
        return new int[]{-1,-1};
    }

    /**
     * 二分查找的特性就是会去找元素的第一个位置。
     * 所以我们不必不去找最后的位置
     * 而是去找下一个比target大的元素的开始位置 rightIndex
     * lower为true找第一个大于等于 target 的位置 也就是leftIndex;
     * lower为false 就去找第一个大于target的位置 rightIndex
     * @param nums
     * @param target
     * @param lower
     * @return
     */
    public int binarySearch(int[] nums,int target,boolean lower){
        int left = 0,right = nums.length - 1,ans = nums.length;

        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)){
                right = mid - 1;
                ans = mid;
            }
            else left = mid + 1;
        }

        return ans;
    }
}

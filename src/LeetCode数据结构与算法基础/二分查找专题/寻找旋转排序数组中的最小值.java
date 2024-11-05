package LeetCode数据结构与算法基础.二分查找专题;

/**
 * @author by KingOfTetris
 * @date 2024/11/5
 */


/**
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，
 * 并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * <p>
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */

//和寻找元素相比，这里只需要去找最小值。
public class 寻找旋转排序数组中的最小值 {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            //我们需要判断 mid 和数组最小值的位置关系，谁在左边，谁在右边？
            //把 mid 与最后一个数 nums[n−1] 比大小
            // [3,4,5,6,2] 如果是大于的情况
            if (nums[mid] > nums[nums.length - 1]) {
                left = mid + 1;//如果大于，则min 在 mid 后面
            }
            // 如果是[2,3,4,5,6] mid < n-1
            else {
                right = mid - 1;//如果小于等于，则min 应该在mid前面
            }
        }
        return nums[left];
    }

}

package LeetCode_HOT100题;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2022/10/17
 */
public class 搜索插入位置 {


    @Test
    public void test(){
        int[] nums = {1,3,5,6};
        int i = searchInsert(nums, 2);
        System.out.print(i);
    }

    /**
     * 返回target在升序数组nums中的插入索引
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {

        //两种特殊情况 直接插入
        if (target < nums[0]) return 0;
        if (target > nums[nums.length - 1]) return nums.length;

        int left = 0,right = nums.length - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target){//如果刚好相等，那直接在mid的位置插入
                return mid;
            }
            else if (nums[mid] > target){
                right = mid - 1;
            }
            else if (nums[mid] < target){
                left = mid + 1;
            }
        }
        return left;//最后left就是要插入的位置
    }
}

package LeetCode数据结构与算法基础.二分查找专题;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/11/5
 */


//和I的区别就是，II的数组中加上了重复元素，问你数组中存不存在target
public class 搜索旋转排序数组II {

    @Test
    public void test() {
//        int[] nums = {2,5,6,0,0,1,2};
        int[] nums = {1, 0, 1, 1, 1};
        boolean search = search(nums, 0);
        System.out.println(search);
    }

    //[2,5,6,0,0,1,2]
    //0 true
    //3 false
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //这里毕竟特殊，有重复值，得先判断
            //对于数组中有重复元素的情况，
            // 二分查找时可能会有 a[l]=a[mid]=a[r]，此时无法判断区间 [l,mid] 和区间 [mid+1,r] 哪个是有序的。
            //就比如[1,0,1,1,1]去找0
            //虽然你知道[0,0][1,n-1]才是有序区间，但是普通的二分没法让指针指到[0,1,1,1]的前半部分
            //他会直接跳到mid + 1 = 2 + 1，忽略了0的位置

            /**
             * 对于这种情况，我们只能将当前二分区间的左边界加一，右边界减一，然后在新区间上继续二分查找。
             * 结果  时间复杂度直接就变成了 O(N) 那你还搞什么二分，真无语了。
             */
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                ++left;
                --right;
            }
            else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        //找不到
        return false;
    }
}
   /*  作者：力扣官方题解
            链接：https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/solutions/704686/sou-suo-xuan-zhuan-pai-xu-shu-zu-ii-by-l-0nmp/
            来源：力扣（LeetCode）
            著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/















package LeetCode_HOT100题;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2022/10/17
 */

//给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
//如果数组中不存在目标值 target，返回 [-1, -1]。
//你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。

//查找x出现的第一个位置就是 find(x),最后一个就是find(x + 1) - 1
//如果不存在x+1 就回返回数组长度，那么数组长度-1 前面也还是x 也是符合要求的。
public class 在排序数组中查找元素的第一个和最后一个位置 {
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int[] res = searchRange(nums, 8);
        System.out.println(Arrays.toString(res));
    }

    //先找>=target的第一个 找到第一个位置
    //再找>target的第一个  找到最后一个位置
    public static int[] searchRange(int[] nums, int target) {
        int l = search_lower(nums, target);
        //5,7,7,8,8,10
        //找到比target大的第一个元素，再-1就是target最后出现的序号
        //比如这里就是去找9出现的第一次下标，很明显是找不到的。
        //那么left会返回数组下标的最后一个位置就是n-1，也就是10所在的位置
        //那么 n - 2就8出现的最后一个位置

        //那么如果是5,7,7,8,8呢?
        //那么left 会越过边界，到达n。那么n-1还是最后一个8的位置。
        int r = search_lower(nums, target + 1);
        //如果l根本就不等于target或者l==n
        //说明数组中根本不存在target.
        if (l == nums.length || nums[l] != target)
            return new int[]{-1, -1};
        //其实这里还有种变体
        // 如果不存在则插入到它应该在的位置。
        return new int[]{l, r - 1};
    }

    //找>=target的第一个 注意这个是左闭右开区间

    /**
     * 我就是喜欢闭区间，就搞那么麻烦了。
     * @param nums
     * @param target
     * @return
     */
    //找到数字第一次出现的位置
    public static int search_lower(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (r + l) / 2; //简单写法，以前可能会有加法溢出的问题，python其实无所谓。
            //5 7 7 8 8 10
            //比如找8
            if (nums[mid] >= target)
                r = mid - 1;
            // 当【mid小于target】时，target必在mid的右侧，将low提高至mid+1d
            else
                l = mid + 1;
        }
        //最后left就指向了第一个位置
        return l;
    }
}

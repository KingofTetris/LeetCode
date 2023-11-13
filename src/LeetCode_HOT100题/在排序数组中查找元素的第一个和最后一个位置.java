package LeetCode_HOT100题;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2022/10/17
 */
public class 在排序数组中查找元素的第一个和最后一个位置 {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int n = random.nextInt(10, 11);
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = random.nextInt(1, 10);
        }
        Arrays.sort(nums);
        for (int num : nums) {
            System.out.print(num + "\t");
        }
        System.out.println();
        int[] ints = searchRange(nums, 8);
        for (int anInt : ints) {
            System.out.print(anInt + "\t");
        }
    }

    //先找>=target的第一个 找到第一个位置
    //再找>target的第一个  找到最后一个位置
    public static int[] searchRange(int[] nums, int target) {
        int l = search(nums, target);
        int r = search(nums, target + 1);
        //如果l根本就不等于target或者l==n
        //说明数组中根本不存在target.
        if (l == nums.length || nums[l] != target)
            return new int[]{-1, -1};
        return new int[]{l, r - 1};
    }

    //找>=target的第一个
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (r + l) / 2;
            //如果mid >= target 缩小right 去找最左边的target
            if (nums[mid] >= target)
                r = mid;
            //如果 mid < target 那么放大l 去找更大的mid
            else
                l = mid + 1;
        }
        return l;
    }
}

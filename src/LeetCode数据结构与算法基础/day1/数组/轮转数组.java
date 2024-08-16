package LeetCode数据结构与算法基础.day1.数组;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/15
 */
public class 轮转数组 {

    @Test
    public void test(){
//        int[] nums = {1,2,3,4,5,6,7};
        int[] nums = {-1};
        int k = 2;
        rotate(nums,k);
    }

    //利用额外空间的做法
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        /**
         * src – the source array.
         * srcPos – starting position in the source array.
         * dest – the destination array.
         * destPos – starting position in the destination data.
         * length – the number of array elements to be copied
         */
        System.arraycopy(newArr, 0, nums, 0, n);
/*
        作者：力扣官方题解
        链接：https://leetcode.cn/problems/rotate-array/solutions/551039/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }

    //原地反转的做法
    public void rotate(int[] nums, int k) {
        //将数组右移K位
        int n = nums.length;
        k = k % n; //取余，例如长度为7的数组右移9位相当于2位。
        // 2 % 1 == 0，一位的数组没必要移动。
        changePos(nums, 0, n - k - 1);
        changePos(nums, n - k, n - 1);
        changePos(nums, 0, n - 1);
        System.out.println(Arrays.toString(nums));
    }

    public void changePos(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

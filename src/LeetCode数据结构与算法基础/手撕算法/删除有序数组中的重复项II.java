package LeetCode数据结构与算法基础.手撕算法;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/10/25
 */
public class 删除有序数组中的重复项II {


    @Test
    public void test(){
        int[] nums = {1,1,1,2,2,3};
        int duplicates = removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
    }
    /**
     * 原地删除重复元素，但是不是只留一个，要让出现2次以上的，只保留两个。
     * 返回按条件保留的长度，并修改数组
     *
     * @param nums
     * @return
     */
    //还是可以用双指针解法，但是稍微修改一下。但是我不太喜欢这题双指针的写法，太绕。
    //用下面一个k来记录。零茶山艾府 才发现原来是0x3F
    //1,1,1,2,2,3
    //实际上这个解法也适用于第一题。 只是K改成1，所以是这个K = N的更一般的解法。
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) { //这里要修改成K 就是更一般的解法
            return nums.length;
        }
        int k = 2; //注意K = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[k - 2]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }


    //因为给定的数组已经是有序的，可以直接利用这个特性。
    //直接进行赋值即可
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 2 || num > nums[i - 2]) {
                nums[i++] = num;
            }
        }
        return i;
    }
}

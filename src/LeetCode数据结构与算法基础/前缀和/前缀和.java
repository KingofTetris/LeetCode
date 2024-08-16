package LeetCode数据结构与算法基础.前缀和;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class 前缀和 {
    @Test
    public void test(){
        Random random = new Random();
        int[] nums = new int[random.nextInt(10,12)];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(11);
        }
        System.out.println(Arrays.toString(nums));
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2));
    }
}

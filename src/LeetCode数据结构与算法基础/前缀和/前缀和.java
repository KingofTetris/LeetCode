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
        int[] preSum = new int[nums.length + 1];
        //为了让下标从1开始，把0填充为0。
        preSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(11);
        }

        for (int i = 0; i < nums.length; i++) {
            //计算[1,1]到[1,n]的前缀和
            preSum[i + 1] = preSum[i] + nums[i];
        }
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(preSum));
        //求区间[i,j]的和，i从1开始
        //j>=i
        for (int i = 1; i <= nums.length; i++) {
            for (int j = i; j <= nums.length; j++) {
                int sum = preSum[j] - preSum[i - 1];
                System.out.println("区间:[" + i + "," + j + "]的和为" + sum );
            }
        }
    }
}

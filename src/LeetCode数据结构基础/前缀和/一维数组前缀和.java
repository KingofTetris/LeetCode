package LeetCode数据结构基础.前缀和;

import java.util.Random;

/**
 * @author by KingOfTetris
 * @date 2023/4/24
 */
public class 一维数组前缀和 {
    public static void main(String[] args) {
        int[] nums = new int[5];//假如给你10个数，请你计算指定子区间之和
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(10);//0-9之间的随机数
        }
        NumArray numArray = new NumArray(nums);//一般没必要再多弄一个前缀和数组出来，就用原来的数组构建前缀和数组就行
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();

        System.out.println(numArray.sumRange(0, 3));
    }
}

package LeetCode_HOT100题;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/6/13
 */
public class _41不在数组中的最小正整数 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Random random = new Random();//不写随机种子则以为当前时间为默认值，写上随机种子规则，则每次随机都是一样的。
        int[] nums = new int[n];
        nums[0] = 1;
        nums[n-1] = n;
        for (int i = 1; i < n - 1; i++) {
            nums[i] = random.nextInt(n);//随机生成整数数组
        }
        for (int num : nums) {
            System.out.print(num + "\t");
        }
        System.out.println();
        int res = solution1(nums);
        System.out.println(res);
    }


    //需要O(n)的额外空间。
    private static int solution1(int[] nums) {
        int N = nums.length;
        int[] flag = new int[N + 1];//记录从1到N的数是否出现。N+1避免N越界
        //记录1到n是否在标记中出现
        for (int num : nums) {
            if (num >= 1 && num <= N) flag[num] = 1;
        }
        //从左往右遍历这个标记数组 谁没出现 谁就是最小的正整数
        for (int i = 1; i < flag.length; i++) {
            if (flag[i] == 0)
                return i;
        }
        //都出现了就返回N+1
        return N + 1;
    }

    //优化为O(N) 不用额外空间
    private static int solution2(int[] nums){
        int N = nums.length;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 1 && nums[i] <= N && nums[nums[i] -1 ] != nums[i]){
                swap(nums,i,nums[i] - 1);
            }
        }
        //交换完毕以后再从左往右遍历
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return N + 1;
    }

    private static void swap(int[] nums,int i ,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

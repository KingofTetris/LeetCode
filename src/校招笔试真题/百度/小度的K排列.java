package 校招笔试真题.百度;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/12
 */
public class 小度的K排列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        //排列是指1-len的长度中，1-len每个数字都出现并且只出现一次！
        checkPermutation(nums,k);
    }

    //判断是否存在只用交换0或1次元素就能得到长为K的连续排列?
    //如果不行输出no
    //如果可以输出yes 0 或者yes 1 u,v uv是需要交换的下标
    //开一个长度为k的滑动窗口，从左到右判断即可。
    public static void checkPermutation(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int right = k - 1;
        //查看一下left->right之间是否 1到k 中至少出现了k-1个数
        int[] flags = new int[k];
        while (right < n) {
            for (int i = left; i <= right; i++) {
                if (nums[i] <= k) {
                    //nums[i] - 1
                    flags[nums[i] - 1]++;
                }
            }
            int sum = Arrays.stream(flags).sum();
//            OptionalInt min = Arrays.stream(flags).min();
            //刚好都出现，那么直接返回yes
            if (sum == k) {
                System.out.println("YES");
                System.out.println(0);
                return;
            }
            //如果刚好缺一个，那么就要去找这个缺失的数字和当前位置的元素交换
            if (sum == k - 1) {
                //先找到u
                int u = 0;
                for (int i = left; i < right; i++) {
                    if (nums[i] > k) {
                        u = i + 1;
                    }
                }
                //再去找v
                int missing = 0;
                for (int i = 0; i < flags.length; i++) {
                    if (flags[i] == 0) {
                        missing = i + 1; //找到缺少的数字
                    }
                }
                int v = 0;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == missing) {
                        v = i + 1;
                    }
                }
                System.out.println("YES");
                System.out.println(1);
                System.out.println(u + " " + v);
                return;
            }
            //如果sum < k-1
            //说明这段窗口至少缺少2个。继续找下一个
            if (sum < k - 1) {
                //数组归0
                Arrays.fill(flags,0);
            }
            right++;
            left++;
        }
        System.out.println("No");
    }

}

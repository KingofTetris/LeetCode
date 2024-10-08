package LeetCode数据结构与算法基础.数学;

import java.util.Scanner;

public class 小O的或运算 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            //因为或运算只会更大，不会更小
            //与运算只会更小不会更大。
            if (nums[i] > k) { //如果nums[i] > k 那么不管怎么分，都不可能让所有段<=k
                System.out.println(-1);
                return;
            }
        }


        int tmp = 0;
        int res = 0;

        //每一个去或就行了
        for (int i = 0; i < n; i++) {
            tmp |= nums[i];
            //如果tmp > k 了，切换到下一个数字
            if (tmp > k) {
                //如果tmp > k 就说明 前面那段 <= k
                //结果 +1
                res++;
                //tmp = cur
                tmp = nums[i];
            }
        }
        //如果本来全部相或就<=k res++ 至少可以分成1段
        if (tmp <= k) {
            res++;
        }
        System.out.println(res);
    }
}


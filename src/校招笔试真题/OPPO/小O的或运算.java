package 校招笔试真题.OPPO;

import java.util.Scanner;

public class 小O的或运算 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
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
            if (tmp > k) {
                res++;
                tmp = nums[i];
            }
        }
        //最后一个数如果<=k res++
        if (tmp <= k) {
            res++;
        }
        System.out.println(res);
    }
}


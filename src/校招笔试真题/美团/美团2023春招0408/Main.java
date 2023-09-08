package 校招笔试真题.美团.美团2023春招0408;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */
public class Main {

    //从1-n 组成一个数列，使得相邻元素之和都不是素数
    //有多少种排列？
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
  /*      if (n == 2 || n == 3) {
            System.out.println(0);
            return;
        }
        if (n == 5) {
            System.out.println(4);
            return;
        }*/
        int nums[] = new int[n];
        //现在要找出1-n每个数字用一次，排列所有可能的数组
        long res = 0;
        for (int i = 1; i <= n; i++) {
            int[] count = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                count[1] = 1;
            }
            //nums[i] 1-n选一个
            for (int j = 0; j < n; j++) {
                if (count[j + 1] != 0){
                    nums[i] = j + 1;
                }
            }
            if (isArrSS(nums)) res++;
        }
        //怎么找所有的数组呢??
        System.out.println(res);

    }
    //判断是否是素数
    public static boolean NotSS(int nums){
        //如果从2到根号n 有一个数相余是0，那就不是素数。
        for (int i = 2; i < Math.sqrt(nums); i++) {
            if (nums % i == 0) return true;
        }
        return false;
    }

    public static boolean isArrSS(int[] num){
        int n = num.length;
        int i = 0;
        while(true) {
            int j = i + 1;
            if (j == n) break; //j = n 跳出
            int sum = num[i] + num[j];
            //如果相邻元素之和是素数，
            if (!NotSS(sum)) return false;
            i++;
        }
        //相邻元素之和都不是素数 OK 返回true
        return true;
    }
}

package 校招笔试真题.阿里云;

import java.util.*;

public class 修改最少次数的递增数组 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n + 1];
        for(int i = 1;i <= n;i++){
            nums[i] = sc.nextInt();
        }

        //实际上操作会让相邻的数每次差距+1
        //实际上你只需要找到相邻差距最大的两个不递增对a[i] a[i + 1]
        //查看让a[i + 1] > a[i] 要多少次就是答案
        int max = 0;
        for(int i = 1;i < n;i++){
            int temp = nums[i] - nums[i+1];
            if( temp >= 0){
                //结果就是 Math.max(max,nums[i] - nums[i+1] +1)
                max = Math.max(max,temp + 1);
            }
        }
        System.out.println(max);
    }


}















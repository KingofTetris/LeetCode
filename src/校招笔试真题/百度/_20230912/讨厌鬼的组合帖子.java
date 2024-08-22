package 校招笔试真题.百度._20230912;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/22
 */
public class 讨厌鬼的组合帖子 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for(int i = 0;i < n;i++){
            a[i] = sc.nextInt();
        }
        for(int i = 0;i < n;i++){
            b[i] = sc.nextInt();
        }
        //每个物品只能选一次
        //怎么才能使得|x - y|最大

        //实际上就两种可能才会让这个差值增大
        //
        //把所有x > y 的组合在一起
        //和把所有 x < y的组合在一起。然后比较两者的差值
        long res1 = 0;
        long res2 = 0;
        for(int i = 0;i < n;i++){
            if(a[i] > b[i] ){
                res1 += (a[i] - b[i]);
            }
            if(a[i] < b[i]){
                res2 += (b[i] - a[i]);
            }
        }
        long max = Math.max(res1,res2);
        System.out.println(max);
    }
}

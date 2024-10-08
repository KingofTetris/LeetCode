package 校招笔试真题.京东.秋招20230826;

import java.util.Scanner;

public class 塔子哥购物 {

    //AK
    public static void main(String[] agrs){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];
        //开始赋值
        for(int i = 0;i<n;i++){
            A[i] = sc.nextInt();
        }
         for(int i = 0;i<n;i++){
            B[i] = sc.nextInt();
        }
         for(int i = 0;i<n;i++){
            C[i] = sc.nextInt();
        }
         for(int i = 0;i<n;i++){
            D[i] = sc.nextInt();
        }
        int res = 0;
        //开始比较
        for(int i = 0;i<n;i++){
            if(B[i] > A[i] && D[i] < C[i]){
                res++;//买B
            }
            if(B[i] < A[i] && D[i] > C[i]){
                res++;//买A
            }
            //其他情况不买。
        }
        System.out.println(res);
    }
}

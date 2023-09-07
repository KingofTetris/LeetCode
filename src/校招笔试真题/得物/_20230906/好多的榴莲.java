package 校招笔试真题.得物._20230906;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/6
 */


//有序数组中两数之和。
//R-L+1堆榴莲，第一堆L，第i堆，L+i-1,最后一堆R
    //请问从这堆榴莲中选择两堆组成m有多少种方案？
public class 好多的榴莲 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0){
            int L = sc.nextInt();
            int R = sc.nextInt();
            int m = sc.nextInt();
            solution(L,R,m);
        }
    }

    private static void solution(int left, int right, int m) {
        // 设为x y堆
        //则 L+x-1 L+y-1 = 2L +x+y -2 = m
        //x+y = m - 2L + 2
        //变成从1到R-L+1中选择 m - 2L +2 的可能
        int len = right - left + 1;
        //MD原来坑在这里，2*L 不是2l!!
        int res = 0;
        int target = m - 2*left +2;
        //可以先二分对半砍。
        int mid = (left + right) / 2;
        while (mid >= target){
            mid = mid - 1;
        }
        for (int i = 1; i <= mid; i++) {
            for (int j = i + 1; j <= mid; j++) {
                if (i + j == target){
                    res++;
                }
            }
        }
        System.out.println(res);
    }
}

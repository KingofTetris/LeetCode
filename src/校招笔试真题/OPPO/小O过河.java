package 校招笔试真题.OPPO;

import java.util.Scanner;

public class 小O过河 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] num = new int[n];
        int sta = -1;
        int res = 0;
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            //如果这一步是石头，那么就更新res
            //res = max(res,i - sta)
            // sta = i
            if (num[i] == 1) {
                res = Math.max(res,i - sta);
                sta = i;
            }
        }
        res = Math.max(res,n - sta);
        System.out.println(res);
    }
}

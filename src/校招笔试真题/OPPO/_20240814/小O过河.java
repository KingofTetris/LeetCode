package 校招笔试真题.OPPO._20240814;

import java.util.Scanner;


/**
 * 有一条长度为n 的河流，小O初始位于左岸边（即河流左侧的岸边）。
 * 他想要跨越到河对岸（即河流右侧的岸边）。河上有一些石头可以让小O踩在上面。
 * 小O只能踩在石头或者岸边，他想知道他至少能够到达对岸的情况下，最长的一步最短是多少。
 */
public class 小O过河 {


    /**
     * 要保证能跨过河流，只要保证能跨过最长的两个石头间隙即可，这就是答案
     * @param args
     */
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

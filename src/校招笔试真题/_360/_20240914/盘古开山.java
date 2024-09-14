package 校招笔试真题._360._20240914;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/14
 */
public class 盘古开山 {

    //最长连续区间
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < a.length; i++) {
                a[i] = sc.nextInt();
            }

            //记录每个num的第一个前后连续递增子串长度
            int[] pre = new int[n];
            int[] post = new int[n];
            pre[0] = 0;
            post[n - 1] = 0;

            for (int i = 1; i < n; i++) {
                //找出a的最长连续子数组
                pre[i] = findPre(a, i);
            }

            for (int i = 0; i <= n - 2;i++) {
                post[i] = findPost(a, i);
            }

            int max = 0;
            for (int i = 0; i < n; i++) {
                //如果是前后可以连接的情况
                if (i - 1 >= 0 && i + 1 < n && a[i - 1] + 1 < a[i + 1]) {
                    max = Math.max(max,pre[i] + post[i] + 1);
                }
                else{
                    max = Math.max(max,pre[i] + 1);
                    max = Math.max(max,post[i] + 1);
                }
            }
            System.out.println(max);
            //但是可能存在两个连续区间之间刚好隔了一个数组，让他们不递增，但是把他修改为某个数又可以递增了
            //比如 1 2 3 6 5 6 7
            //把中间的6改成4 则全部递增
            //所以其实你应该去找每个数字的前后第一个连续递增区间
            //如果前面递增区间的last < 后面递增区间的first
            //那么中间修改就可以把两边连起来。
            //其他都不行。

        }
    }

    private static int findPre(int[] a, int index) {
        //至少自己是一个连续递增序列
        int res = 1;
        for (int i = index - 1; i >= 0; i--) {
            if (i - 1 >= 0 && a[i] > a[i - 1]) {
                res++;
            }
            else if (i - 1 >= 0 && a[i] < a[i - 1]){
                //如果不是递增的，直接结束
                break;
            }
        }
        return res;
    }

    private static int findPost(int[] a, int index) {
        int res = 1;
        int n = a.length;
        for (int i = index + 1; i < n; i++) {
            if (i + 1 < n && a[i] < a[i + 1]) {
                res++;
            }
            else if (i + 1 < n && a[i] > a[i + 1]){
                break;
            }
        }
        return res;
    }
}

package 校招笔试真题.金山办公._20241109;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */

//这个B题什么意思?这能0%??用例绝壁有问题

public class 小红的子树操作 {

    static int[] a;
    static int[] b;
    static long[] memo;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        a = new int[n + 1];
        b = new int[n + 1];
        memo = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            adj[start].add(end);
        }
        int q = sc.nextInt();//操作次数
        while (q-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            //首先要把x为根的子树的所有值乘以y
            dfs(x, y);
        }
        //dfs赋值完，再计算每个节点的权值
        calProduct(1);//从1出发，把所有值算一遍。memo就不会是0了。
        for (int i = 1; i <= n; i++) {
            if (memo[i] != 0) {
                b[i] = getCountZeros(memo[i]);
            }
        }
        for (int i = 1; i <= n; i++) {
            if (i != n) {
                System.out.print(b[i] + " ");
            } else {
                System.out.print(b[i]);
            }
        }
    }

    private static void dfs(int x, int y) {
        //首先自己先乘x
        a[x] = a[x] * y;
        //然后dfs自己的儿子
        ArrayList<Integer> xChildren = adj[x];
        for (Integer child : xChildren) {
            dfs(child, y);
        }
    }

    public static long calProduct(int root) {
        long product = a[root];
        for (Integer child : adj[root]) {
            product *= calProduct(child);
        }
        memo[root] = product;
        return product;
    }

    public static int getCountZeros(long num) {
        int count = 0;
        while (num != 0) {
            if (num % 10 == 0){
                count++;
            }
            else {
                break;
            }
            num /= 10;
        }
        return count;
    }
}

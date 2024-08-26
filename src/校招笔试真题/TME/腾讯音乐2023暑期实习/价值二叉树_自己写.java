package 校招笔试真题.TME.腾讯音乐2023暑期实习;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/8/26
 */
public class 价值二叉树_自己写 {

    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int[] weight;
    static int[] res;
    static int[] product;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        adj = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        weight = new int[n + 1];
        res = new int[n + 1];
        //因为数据量太大，如果真的去相乘 再去算0的数量 会超时，只能分解成2，5
        //算2，5更小者就是0的数量。
        product = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            weight[i] = sc.nextInt();
            product[i] = weight[i];
        }

        for (int i = 1; i <= n - 1; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            adj[s].add(t);
            adj[t].add(s);
        }

        //然后开始算树的价值
        dfs(1);
        //product算完，开始算0
        for (int i = 1; i <= n; i++) {
            int zeroNums = zeroNums(product[i]);
            res[i] = zeroNums;
        }

        for (int i = 1; i <= n; i++) {
             if (i != n){
                 System.out.print(res[i] + " ");
             }
             else {
                 System.out.print(res[i]);
             }
        }
    }

    private static void dfs(int node) {
        //标记走过了
        visited[node] = true;
        for (Integer neighbor : adj[node]) {
            //如果邻居没走过，往下走
            if (!visited[neighbor]){
                dfs(neighbor);
                //注意product一定要放下面
                product[node] = product[node] * product[neighbor];
            }
        }
    }


    private static int zeroNums(int n){
        int count = 0;
        while (n != 1){
            if (n % 10 == 0){
                n = n / 10;
                count++;
            }
            else {
                //如果不是10的倍数，直接break;
                break;
            }
        }
        return count;
    }
}

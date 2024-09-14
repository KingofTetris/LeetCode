package 校招笔试真题._360._20240914;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/9/14
 */


/**
 * 5 2
 * 8 6 1 5 1
 * 4 4 3 4 6
 *
 * 给你N个点和安全距离K
 * 然后是N个点的坐标，Xi Yi
 * 距离计算|xi - xj| + |yi - yj|
 *
 * 请问如果某一个点成为感染点，那么最多有多少个点会被感染？
 */
public class 传染病防控 {

    static ArrayList<Integer>[] g;
    static int[] x;
    static int[] y;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt(); //安全距离
        x = new int[n];
        y = new int[n];
        //一个完全无向图
        g = new ArrayList[n];

        int index = 0;
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (j != i) g[i].add(j);
            }
        }

        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            y[i] = sc.nextInt();
        }
        int max = 1;
        for (int i = 0; i < n; i++) {
            //每次传一个新的visited进去
            int[] visited = new int[n];
            visited[i] = 1;
            max = Math.max(max, dfs(g, i, visited,k));
        }

        System.out.println(max);
    }

    private static int dfs(ArrayList<Integer>[] g, int i, int[] visited,int k) {
        //去遍历所有点
        int size = 1;
        for (Integer j : g[i]) {
            if (visited[j] == 0 && calDis(x[i],y[i],x[j],y[j]) <= k){
                visited[j] = 1;
                size += dfs(g,j,visited,k);
            }
        }
        return size;
    }

    public static int calDis(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}

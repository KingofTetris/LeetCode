package 校招笔试真题.BOSS直聘._20230919;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/19
 */

/**
 * BOSS能出这种题就NM离谱。
 * 树上DP。属于是竞赛类的题目。
 * 笔试基本没有人能做的出来。所以其实相当于A 2.7/3
 */
public class 加分二叉树 {
    static int[][] f = new int[30][30]; //中序遍历[i,j]对应的最优子树的加分，初始化为-1
    static int[][] root = new int[30][30]; //中序遍历[i,j]对应的最优子树的根编号
    static int[][] tree = new int[30][2]; //tree[i][0]=i的左儿子，tree[i][1]=i的右儿子,空子树为0
    static int[] w = new int[30]; //w[i]=i号节点权值
    static int n;
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // 这一行是为了吸收输入中的换行符，避免影响后续输入
        n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            w[i] = scanner.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                f[i][j] = -1;
            }
        }
        dfs(1, n);
    }

    public static void dfs(int i, int j) { //计算f[i][j],root[i][j]
        if (f[i][j] != -1) return;
        if (i == j) { //是叶节点
            f[i][j] = w[i];
            root[i][j] = i;
            return;
        }
        for (int k = i + 1; k < j; k++) { //两子树都不空
            dfs(i, k - 1);
            dfs(k + 1, j);
            if (f[i][j] < w[k] + f[i][k - 1] * f[k + 1][j]) {
                f[i][j] = w[k] + f[i][k - 1] * f[k + 1][j];
                root[i][j] = k;
            }
        }
        dfs(i + 1, j);
        if (f[i][j] < w[i] + 1 * f[i + 1][j]) {
            f[i][j] = w[i] + 1 * f[i + 1][j];
            root[i][j] = i;
        }
        dfs(i, j - 1);
        if (f[i][j] < w[j] + f[i][j - 1] * 1) {
            f[i][j] = w[j] + f[i][j - 1] * 1;
            root[i][j] = j;
        }
    }

    public static void buildtree(int i, int j) {
        int r = root[i][j];
        if (i == j) {
            tree[r][0] = 0;
            tree[r][1] = 0;
            return;
        }
        if (r == i) {
            buildtree(r + 1, j);
            tree[r][0] = 0;
            tree[r][1] = root[r + 1][j];
            return;
        }
        if (r == j) {
            buildtree(i, r - 1);
            tree[r][0] = root[i][r - 1];
            tree[r][1] = 0;
            return;
        }
        buildtree(i, r - 1);
        tree[r][0] = root[i][r - 1];
        buildtree(r + 1, j);
        tree[r][1] = root[r + 1][j];
    }

    public static void pre(int r) {
        q.offer(r);
        if (tree[r][0] != 0) pre(tree[r][0]);
        if (tree[r][1] != 0) pre(tree[r][1]);
    }

}

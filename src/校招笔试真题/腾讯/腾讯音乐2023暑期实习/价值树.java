package 校招笔试真题.腾讯.腾讯音乐2023暑期实习;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/18
 * 题目描述：
 * 塔子哥是一位著名的计算机科学家，在一次采集森林中的植物时，
 * 偶然发现了一棵非常特殊的树。这棵树是一棵二叉树，其节点上都标有不同的数字。
 * <p>
 * 在细心观察后，塔子哥意识到这棵二叉树是由多个相同的子树组成的，
 * 每个子树的根节点都是同一个数字。他对这个发现感到非常兴奋，并且开始研究如何计算这些子树的价值。
 * <p>
 * 他定义每个节点的价值为其子树节点乘积的末尾 0 的数量，因此，如果一个节点的子树中有 k 个数末尾带有0，那么这个节点的价值就是k
 * <p>
 * 塔子哥想编写一个程序来计算每个节点的价值，以便能够更好地研究这棵树的特性。
 * 他请求您的帮助来实现这个程序，
 * 您需要返回一棵二叉树，树的结构和给定的二叉树相同，将每个节点的权值替换为该节点的价值
 * <p>
 * <p>
 * 二又树节点数不超过 1e5
 * 二又树每个节点的权值都是不超过 1e9 的正整数
 * <p>
 * 输入格式：
 * <p>
 * 第一行包含整数 n，表示二叉树的节点数。
 * <p>
 * 第二行包含 n 个整数，表示每个节点的权值。
 * <p>
 * 接下来 n-1 行，每行包含两个整数 u 和 v，表示节点 u 和节点 v 之间存在一条边。根为 1
 * <p>
 * 输出格式：
 * <p>
 * 输出 n 个整数，表示每个节点的价值。
 * <p>
 * 输入样例：
 * <p>
 * 5
 * 2 5 10 4 5
 * 1 2
 * 1 3
 * 3 4
 * 3 5
 * <p>
 * 输出样例：
 * <p>
 * 3 0 2 0 0
 * <p>
 * 提示：
 * <p>
 * 在本题中，如果一个数末尾有 k 个零，则我们称其有 k 个“末尾零”。
 */
public class 价值树 {
    static ArrayList<Integer>[] adj;
    static int[] weight;
    static int[] res;
    static int[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int T = n;
        weight = new int[n + 1];
        res = new int[n + 1];
        visited = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            weight[i] = sc.nextInt();
        }
        adj = new ArrayList[n + 1];//集合数组进行初始化的时候不需要在new后面指定类型
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        while (T - 1 > 0) {
            int node = sc.nextInt();
            int neighbor = sc.nextInt();
            adj[node].add(neighbor);
            adj[neighbor].add(node);
            T--;
        }

        dfs(1);

        for (int i = 1; i <= n; i++) {
            System.out.print(res[i] + " ");
        }
    }


    private static int[] dfs(int i){
        int[] fj = fj_2_5(weight[i]);
        visited[i] = 1;//遍历以后标记为已遍历，防止DFS来回震荡。
        int[] dfs = new int[2];
        for (Integer neighbor : adj[i]) {
            if (visited[neighbor] != 1){
                dfs = dfs(neighbor);
            }
            fj[0] += dfs[0];
            fj[1] += dfs[1];
        }
        //结果就是2和5质因子较小的那个
        res[i] = Math.min(fj[0],fj[1]);
        return fj;
    }

    private static int[] fj_2_5(int n){
        int count2 = 0,count5 = 0;
        int a = n,b = n;

        while (a != 0){
            if (a % 2 == 0){
                count2++;
                a = a / 2;
            }
            else break;//如果根本不是2的倍数，就跳出
        }

        while (b != 0){
            if (b % 5 == 0){
                count5++;
                b = b / 5;
            }
            else break;
        }

        return new int[]{count2,count5};
    }
}

package 校招笔试真题.小红书.小红书24秋招后端;

import java.util.*;

/**
 * 小红在刷小红书的时候看到了一颗挂着小红薯的小红树，所以小红也想种一颗小红树挂一些小红薯发小红书。
 * <p>
 * 小红有一颗树，每个结点有一个权值，初始时每个节点都是白色。小红每次操作可以选择两个相邻的结点，
 * 如果它们都是白色且权值的和是质数，小红就可以选择其中一个节点染红。
 * <p>
 * 小红想知道最多可以染红多少个节点？
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 256M，其他语言512M
 * 输入描述：
 * 第一行输入一个正整数n，代表节点的数量。
 * 第二行输入n个正整数a_i，代表每个节点的权值。
 * 接下来的n-1行，每行输入两个正整数u,v，代表节点u和节点v有一条边连接。
 * 1 <= n <= 10^5
 * 1<= a_i <= 10^5
 * 1<= u,v <= n
 * 输出描述：
 * 输出一个整数表示答案。
 * 示例1
 * 输入例子：
 * 3
 * 1 2 3
 * 1 2
 * 1 3
 * 输出例子：
 * 1
 * 例子说明：
 * 节点1和节点2权值和为3，是质数，所以小红可以染红节点1或节点2，此时无法再染红其他节点。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class 小红的小红树 {
    public static boolean isPrime(int val) {
        if (val <= 1) {
            return false;
        }
        for (int i = 2; i * i <= val; i++) {
            if (val % i == 0)
                return false;
        }
        return true;
    }

    public static int dfs(int i, int vis[], int prev) {
        int ans = 0;
        //System.out.println(i);
        for (int j : edge[i]) {
            //index 的 matrix
            int temp = 0;
            if (j == prev) continue;
            if (isPrime(val[j] + val[i])) {
                vis[i] = 1;
                temp = dfs(j, vis, i) + 1;
                vis[i] = 0;
            } else {
                temp = dfs(j, vis, i);
            }
            ans += temp;
        }
        return ans;
    }

    static int n;
    static int[] val;
    static ArrayList<Integer>[] edge;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        n = in.nextInt();
        val = new int[n];
        edge = new ArrayList[n];

        // 初始化 edge 数组
        for (int i = 0; i < n; i++) {
            edge[i] = new ArrayList<>();
            val[i] = in.nextInt();
        }
        // 拿到所有的边
        for (int i = 0; i < n - 1; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            edge[x].add(y);
            edge[y].add(x);
        }
        System.out.println(dfs(0, new int[n], -1));
    }

    /*作者：miraclemeei
    链接：https://www.nowcoder.com/exam/test/82588960/submission?examPageSource=Company&pid=55243418&testCallback=https%3A%2F%2Fwww.nowcoder.com%2Fexam%2Fcompany&testclass=%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91
    来源：牛客网*/

    //TODO 看看别人写的和你写的DFS有啥不同。
 /*   static int count = 0;
    static ArrayList<Integer>[] g;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = sc.nextInt();
        int[] weight = new int[n + 1];
        //标记数组
        boolean[] colored = new boolean[n + 1];
        for(int i = 1;i <= n;i++){
            weight[i] = sc.nextInt();
        }
        //邻接表
        g = new ArrayList[n + 1];
        for(int i = 0;i <= n;i++){
            g[i] = new ArrayList<>();
        }

        //读取边
        for(int i = 0;i < n - 1;i++){
            int s = sc.nextInt();
            int t = sc.nextInt();
            g[s].add(t);
        }

        *//**
     * 如果两个相邻节点没有被标记，且和为质数就可以将其中一个节点染色
     *//*
         dfs(g[1],weight,colored,1);

         System.out.println(count);
    }

    public static void dfs(ArrayList<Integer> rootAdj,int[] weight,boolean[] colored,int root){
            if(rootAdj.size() == 0) return; // 叶子节点结束
            int rootDeg = rootAdj.size();
            //从根开始遍历
            for(int node : rootAdj){
                int sum = weight[root] + weight[node];
                if(!colored[root] && !colored[node] && isPrime(sum)){
                    //那就可以选一个染色，问题是选哪个?
                    //尽量选那个染了以后影响少的 ，那问题来了，你怎么知道谁影响小
                    //那就比较度呗，TM 邻接表不是给你了吗
                    int nodeDeg = g[node].size();
                    if(rootDeg > nodeDeg){
                        colored[node] = true;
                        count++;
                    }
                    else{
                        colored[root] = true;
                        count++;
                    }
                }
                dfs(g[node],weight,colored,node);
            }
    }

    public static boolean isPrime(int n){
        //如果n % i == 0说明不是质数
        for(int i = 2;i <= Math.sqrt(n);i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }*/
}
















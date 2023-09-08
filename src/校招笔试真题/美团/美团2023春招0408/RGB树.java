package 校招笔试真题.美团.美团2023春招0408;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/4/11
 *
 * 有一棵 n 个节点的树，树上每个点都有红绿蓝三种颜色中的一种。定义一棵树是多彩的，
 * 当且仅当这棵树同时存在一个红色节点，一个蓝色节点和一个绿色节点。
 * 保证最初这棵树是多彩的，现在要砍掉这棵树的某一条边，请问有多少种砍法，使得砍完之后形成的两棵树都是多彩的。
 * 输入描述
 * 第一行一个整数 n，表示节点个数。
 * 第二行 n-1 个整数 p2, p3, ..., pn，pi 表示树上 i 和 pi 两点之间有一条边。保证给出的一定是一棵树。
 * 第三行一个长度为 n 的字符串，第 i 个字符表示第 i 个节点的初始颜色。
 * 其中 R 表示红色，G 表示绿色，B 表示蓝色。保证字符串只由这三种大写字母构成。
 * 对于全部数据，3≤n≤1e5。
 * 输出描述
 * 输出一行，一个正整数，表示答案。
 * 样例输入
 * 7
 * 1 2 3 1 5 5
 * GBGRRBB
 * 样例输出
 * 1
 */

//TODO 过了 5/12 其余的TTL
public class RGB树 {
   /* static ArrayList<Integer>[] adj;
    static char[] colors;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        adj = new ArrayList[n+1];
        colors = new char[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        //完成邻接表
        for (int i = 2; i <= n; i++) {
            int node = sc.nextInt();
            adj[i].add(node);
            adj[node].add(i);
        }
        String colors_str = sc.next();
        sc.close();
        //完成颜色表
        for (int i = 1; i <= n; i++) {
            colors[i] = colors_str.charAt(i - 1);
        }
        int solution = solution();
        System.out.println(solution);
    }

    //切掉一条边，使得两棵树都是彩色树，一共有多少种方案
    //要去重不然会超时
    private static int solution() {
        //遍历边表
        int ans = 0;
        for (int i = 1; i <= adj.length - 1; i++) {
            for (Integer neighbor : adj[i]) { //假设(i,neighbor)就是要删除的边
                if (neighbor < i) continue;//如果邻居节点小于当前节点i，说明已经删除过了,直接跳过这个邻居节点
                //删除边以后，判断剩下的两棵树是否都是彩色树
                String tree_color1 = dfs(i,neighbor);//从i出发不能往neighbor方向遍历。
                String tree_color2 = dfs(neighbor,i);//从neighbor出发不能往i方向遍历。
                //如果tree_color1和tree_color2都是RGB树 ans++
                if (RGB(tree_color1) && RGB(tree_color2)) ans++;
            }
        }
        return ans;
    }


    //对DFS进行一下优化，在DFS的同时就统计RGB，避免无用计算
    private static String dfs(int i, int j) {
        StringBuffer sb = new StringBuffer();
        sb.append(colors[i]);//先把颜色i加入sb
        if (RGB(sb.toString())) return sb.toString();//如果已经满足直接返回就行了，不用再继续无效递归了
        for (Integer neighbor : adj[i]) {
            if (neighbor != j ){ //如果不是删掉的邻居
                sb.append(dfs(neighbor,i));//就继续从neighbor出发，删除掉(neighbor,i)这条边
            }
        }
        return sb.toString();
    }

    private static boolean RGB(String colors) {
        int[] count = new int[3];
        for (int i = 0; i < colors.length(); i++) {
            count[colorToInt(colors.charAt(i))]++;
        }
        //如果RGB都大于1 那么就RGB树
        return count[0] > 0 && count[1] > 0 && count[2] > 0;
    }

    private static int colorToInt(char c) {
        //把R G B对应成0，1，2
        if (c == 'R') {
            return 0;
        } else if (c == 'G') {
            return 1;
        } else {
            return 2;
        }
    }*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //树上节点个数,1~n
        List<Integer>[] edges = new List[n+1]; //邻接矩阵
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        //第二行n-1个整数p2,p3,p4,,,pn, 其中pi表示节点i和节点pi之间有一条边
        for (int i = 2; i <= n; i++) {
            int p = sc.nextInt();
            edges[p].add(i);
            edges[i].add(p);
        }
        //第三行一个长度为n的字符串,第i个字符表示第i个节点的初识颜色
        char[] colors = new char[n+1];
        sc.nextLine();
        String s = sc.nextLine();
        for (int i = 0; i < n; i++) {
            colors[i+1] = s.charAt(i);
        }
        int[][] dp = new int[n+1][3];
        //dp[i][0]表示以i为根节点的子树的红色数量,dp[i][1]绿色,dp[i][2]蓝色
        boolean[] visited = new boolean[n+1];
        dfs(edges,colors,dp,1,visited);
        Arrays.fill(visited,false);
        System.out.println(dfs2(edges,dp,1,visited));
    }
    static void dfs(List<Integer>[] edges,char[] colors, int[][] dp,int node,boolean[] visited){
        visited[node]=true;
        if(colors[node]=='R'){
            dp[node][0]++;
        }else if(colors[node]=='G'){
            dp[node][1]++;
        }else{
            dp[node][2]++;
        }
        for (int next : edges[node]) {
            if (visited[next]) continue;
            dfs(edges,colors,dp,next,visited);
            for (int i = 0; i < 3; i++) {//累加子树的RGB颜色
                dp[node][i]+=dp[next][i];
            }
        }
    }
    //统计多少种砍法
    static int dfs2(List<Integer>[] edges,int[][] dp,int node,boolean[] visited){
        int res = 0;
        visited[node]= true;
        if(node!=1 && check(dp[node],dp[1])){
            res = 1;
        }
        for (int next : edges[node]){
            if(visited[next]) continue;
            res += dfs2(edges,dp,next,visited);
        }
        return res;
    }

    //核心:因为我们从节点1开始dfs,所以无论给的边是什么样子,都可以认为是以1为根节点,故dp[1]就代表了整棵树的颜色数量
//无论减哪条边,本质拆分的两部分是以某节点为根节点的子树(假设为left),和以1为根节点的整棵树减去这个子树剩下的部分(假设right)
    static boolean check(int[] cur, int[] root) {
        for (int i = 0; i < 3; i++) {
            //cur[i]==0表示left某颜色丢失,cur[i]==root[i]即root[i]-cur[i]==0即right部分某颜色丢失
            if (cur[i] == 0 || cur[i] == root[i]) {
                return false;
            }
        }
        return true;
    }

}

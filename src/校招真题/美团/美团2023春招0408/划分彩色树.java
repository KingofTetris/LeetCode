package 校招真题.美团.美团2023春招0408;

import com.sun.javafx.geom.Edge;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

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
public class 划分彩色树 {
    static ArrayList<Integer>[] adj;
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
    }
}

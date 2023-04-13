package 校招真题.小红书.小红书春招0409;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/11
 * 塔子哥是一个聪明勇敢的探险家。他听说有一棵神秘的树，据说在它的某个节点上藏有一个宝藏。
 * 为了寻找这个宝藏，塔子哥决定前往这棵树所在的深山探险。
 * <p>
 * 经过漫长的征途，塔子哥终于到达了这棵树的附近。他仔细观察这棵树，
 * 发现它非常美丽，树干粗壮，树叶繁茂，枝干交错。但是，他也发现了一个问题：
 * 这棵树的某个节点上藏有宝藏，但是这个节点与其他节点之间的连接关系非常复杂，很难直接找到宝藏。
 * <p>
 * 经过一番思考之后，塔子哥决定采用一种特殊的方法来寻找宝藏。他发现，
 * 如果他能够找到树上的某一条边，并删除它，那么这棵树就会被分成两个部分 A 和 B而。宝藏可能就在其中的某一部分中。
 * <p>
 * 但是，塔子哥也知道，他不能随便删除一条边，因为他需要保证两个部分的节点数的差的绝对值 ||A| - |B||尽可能小，
 * 才能有更大的机会找到宝藏。因此，他需要找到最优的划分方案。
 * 现在，他想请你输出最小的||A| - |B||和最优方案的数量，使得他有更大的机会找到宝藏。
 * <p>
 * 输入描述
 * 第一行一个整数 n 表示节点的数量，节点从 11 到 n 编号。
 * 接下来 n−1 行每行两个正整数 s ， t ，表示 s 的父亲是 t 。
 * 输入保证是一棵树。
 * 对于所有数据 1≤n≤100000 。
 * 输出描述
 * 输出一行两个整数，用空格分开，分别表示最优解和最优方案数。
 * 样例
 * 输入
 * 3
 * 2 1
 * 3 1
 * 输出
 * 1 2
 */
//这棵树又有n-1条边
//每次删边深搜最差深度是n-1
//那么整个复杂度就是 (n-1)*(n-1) = O(n^2)
//用二维memo来存储中间结果 结果MLE 还是5分
public class 拆分树 {
    static ArrayList<Integer>[] adj;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        while (n - 1 > 0){
            int node = sc.nextInt();
            int neighbor = sc.nextInt();
            adj[node].add(neighbor);
            adj[neighbor].add(node);
            n--;
        }

        sc.close();

        int[] solution = solution();

        System.out.println(solution[0] + " " + solution[1]);
    }


    /**
     * 删掉一条边 使得 ||A| - |B||最小,最小是多少？有多少种方案
     * @return
     */
    private static int[] solution() {
        int min = Integer.MAX_VALUE;
        int num = 0;
        for (int i = 1; i <= adj.length - 1; i++) { //adj是n+1，adj.length-1才是n
            for (Integer neighbor : adj[i]) {
                if (neighbor < i) continue;//重复删边没有意义

                //算去掉边(i,neighbor)以后，两个连通分量的大小
                int componentSize1 = dfs(i,neighbor);
                int componentSize2 = dfs(neighbor,i);

                //算diff,取min和diff的较小值
                int diff = Math.abs(componentSize1 - componentSize2);
                if (diff < min){
                    min = diff;
                    num = 1;//有了更小的就把num置为1
                }
                else if (diff == min){
                    num++; //如果相等num++;
                }
                //diff > min什么都不做
            }
        }
        return new int[]{min,num};
    }

    /**
     * 塔子哥刷题网站上大佬的留言
     * dfs 没有问题，关键是这么用dfs不对。dfs 时间复杂度是 O(n)，
     * 你跑了至少 m （边数） 次dfs，(??但是这题的边数为 n - 1 条啊大哥)
     * 那肯定会超时啊。
     * 应该想办法把dfs得到的数据存下来，避免重复计算。
     * 你这么做，实际上重复计算了很多次同一个节点的子树大小。
     * @param i
     * @param j
     * @return
     */
    private static int dfs(int i, int j) {
        int size = 1;//一个点就是1
        for (Integer neighbor : adj[i]) {
            if (neighbor != j){ //排除j的DFS搜索
                size += dfs(neighbor,i);
            }
        }
        return size;
    }

}

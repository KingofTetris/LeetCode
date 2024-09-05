package 校招笔试真题.米哈游._20240803;

import java.util.*;


/**
 * 给你一个基环树(n个节点n条边的图) 现在xiaoyo和Pyrmont在玩这个游戏
 * xiaoyo先删除一个度为1的节点和边，然后Pyrmont再删除一个度为1的节点和边
 *
 * 直到有人删除掉基环树中特殊的X节点，取得获胜。
 * 请你返回获胜的玩家
 *
 * 如果没有赢家，返回Draw
 *
 * 比较基础的博弈论
 *
 * 如果目标节点x在环中，结果必然是平局(Draw)。
 * 如果x不在环中:
 * 计算在删除x之前(包括x)可删除的节点数cnt。
 * cnt为偶数，Xiaoyo无法删除该点，Pyrmont获胜。
 * cnt为奇数，Xiaoyo获胜。
 */
import java.util.*;

public class 基环树游戏 {

    // 主函数，用于处理多个测试用例
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // 读取测试组数 T
        while (T-- > 0) {  // 逐组处理测试数据
            solve(sc);  // 调用 solve 函数处理每组数据
        }
        sc.close();  // 关闭输入流
    }

    // 处理每组测试数据的函数
    public static void solve(Scanner sc) {
        int n = sc.nextInt();  // 读取节点数 n
        int x = sc.nextInt();  // 读取目标节点 x

        // 创建邻接表来表示树的结构
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());  // 初始化每个节点的邻接列表
        }

        // 度数数组，用来存储每个节点的度数
        /**
         * 度数数组，经常用来拓扑排序。
         * 当一个节点的度为1的时候 就把他删去
         */
        int[] degree = new int[n + 1];

        // 读取树的边，并构建邻接表
        for (int i = 0; i < n; i++) {
            int u = sc.nextInt();  // 读取一条边的一个节点 u
            int v = sc.nextInt();  // 读取这条边的另一个节点 v
            adjList.get(u).add(v);  // 在 u 的邻接列表中添加 v
            adjList.get(v).add(u);  // 在 v 的邻接列表中添加 u
            degree[u]++;  // 增加 u 的度数
            degree[v]++;  // 增加 v 的度数
        }

        // 使用双端队列（deque）来模拟叶子节点的队列
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 1) {  // 如果节点 i 是叶子节点
                if (i == x) {  // 如果这个叶子节点正好是目标节点 x
                    System.out.println("Xiaoyo");  // Xiaoyo 直接胜利
                    return;  // 直接结束这次测试
                }
                queue.addLast(i);  // 将叶子节点添加到队列末尾
            }
        }

        int removedCount = 0;  // 记录被移除的节点数
        boolean targetFound = false;  // 标记是否找到目标节点 x

        // 处理队列中的节点，模拟移除叶子节点的过程
        while (!queue.isEmpty()) {
            int current = queue.pollFirst();  // 从队列前端取出一个节点
            removedCount++;  // 增加被移除的节点计数
            if (current == x) {  // 如果当前节点是目标节点 x
                targetFound = true;  // 标记已找到目标节点
                continue;  // 继续处理其他节点
            }
            // 对当前节点的每个邻居节点进行处理
            for (int neighbor : adjList.get(current)) {
                degree[neighbor]--;  // 当前节点被移除后，邻居节点的度数减 1
                if (degree[neighbor] == 1) {  // 如果邻居节点变成了叶子节点
                    queue.addLast(neighbor);  // 将它添加到队列末尾
                }
            }
        }

        // 根据是否找到目标节点以及被移除的节点数判断结果
        if (!targetFound) {
            System.out.println("Draw");  // 如果没有找到目标节点，结果是平局
        } else if (removedCount % 2 == 0) {
            System.out.println("Pyrmont");  // 如果移除的节点数为偶数，Pyrmont 胜利
        } else {
            System.out.println("Xiaoyo");  // 如果移除的节点数为奇数，Xiaoyo 胜利
        }
    }


}

package LeetCode数据结构与算法基础.图与并查集;

/**
 * @author KingofTetris
 * @File 钥匙和房间
 * @Time 2021/10/28  11:36
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，
 并且房间里可能有一些钥匙能使你进入下一个房间。

 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，
 每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。
 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。

        最初，除 0 号房间外的其余所有房间都被锁住。

        你可以自由地在房间之间来回走动。

        如果能进入每个房间返回 true，否则返回 false。

        示例 1：

        输入: [[1],[2],[3],[]]
        输出: true
        解释:
        我们从 0 号房间开始，拿到钥匙 1。
        之后我们去 1 号房间，拿到钥匙 2。
        然后我们去 2 号房间，拿到钥匙 3。
        最后我们去了 3 号房间。
        由于我们能够进入每个房间，我们返回 true。
        示例 2：

        输入：[[1,3],[3,0,1],[2],[0]]
        输出：false
        解释：我们不能进入 2 号房间。
        提示：

        1 <= rooms.length <= 1000
        0 <= rooms[i].length <= 1000
        所有房间中的钥匙数量总计不超过 3000。*/

//当 x 号房间中有 y 号房间的钥匙时，我们就可以从 x 号房间去往 y 号房间。
// 如果我们将这 n 个房间看成有向图中的 n 个节点，那么上述关系就可以看作是图中的 x 号点到 y 号点的一条有向边。
//
//这样一来，问题就变成了给定一张有向图，询问从 0 号节点出发是否能够到达所有的节点。
//
public class 钥匙和房间 {
    //BFS
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size(), num = 0;//num统计可以到达的节点个数
        //vis数组标记房间0 - n-1是不是访问过了
        boolean[] vis = new boolean[n];
        Queue<Integer> que = new LinkedList<Integer>();
        vis[0] = true;
        que.offer(0);
        while (!que.isEmpty()) {
            int x = que.poll();
            num++;
            for (int it : rooms.get(x)) {
                //如果x钥匙对应的房间没访问过，就进去康康
                if (!vis[it]) {
                    //标记为已康过，并入队
                    vis[it] = true;
                    que.offer(it);
                }
            }
        }
        return num == n;
    }



    //DFS
    int num;
    boolean[] vis;
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int n = rooms.size();
        num = 0;
        vis = new boolean[n];
        dfs(rooms, 0);
        return num == n;
    }

    public void dfs(List<List<Integer>> rooms, int x) {
        vis[x] = true;
        num++;
        for (int it : rooms.get(x)) {
            if (!vis[it]) {
                dfs(rooms, it);
            }
        }
    }

}

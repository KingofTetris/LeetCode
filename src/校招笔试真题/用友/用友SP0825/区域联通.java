package 校招笔试真题.用友.用友SP0825;

/**
 * @author by KingOfTetris
 * @date 2023/9/6
 */
//返回连同所有区域的最小权值，如果无法连同返回-1
import java.util.*;
class edge{
    public int start, end, value;
    public edge(int s, int e, int v) {
        this.start = s;
        this.end = e;
        this.value = v;
    }
}

public class 区域联通 {

    //并查集查找节点i的父节点
    public static int getparent(int[] p, int id) {
        int father = p[id];
        while (p[father] != father) {
            father = p[father];
        }
        p[id] = father;
        return father;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        //优先队列存放最大的边。
        //JDK8就可以使用Comparator的lambda表达式 按照指定规则从小到大排序，如果你加上-号就是从大到小排列。
        //PriorityQueue默认是小根堆
        PriorityQueue<edge> q = new PriorityQueue<>((Comparator.comparingInt(o -> o.value)));
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt(), end = sc.nextInt(), value = sc.nextInt();
            q.add(new edge(start, end, value));
        }
        //并查集初始化，每个节点的parent初始化为自己
        //用并查集就不需要使用邻接矩阵了。
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;

        int result = 0, count = 0;
        while (!q.isEmpty()) {
            edge temp = q.poll(); //每次选权值最大的边
            int fstart = getparent(parent, temp.start),
                fend = getparent(parent, temp.end);
            //避免成环，如果形成环就下一条。
            if (fstart == fend) {
                continue;
            } else {
                parent[fend] = fstart;
                count += 1;
                result += temp.value;
            }
        }
        if (count < n - 1) //要连通则必须要n-1条边。
            System.out.println(-1);
        else
            System.out.println(result);
        sc.close();
    }
}


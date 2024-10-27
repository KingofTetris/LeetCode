package LeetCode数据结构与算法基础.拓扑排序;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class 课程表II {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> res = new LinkedList<>();
        int[] inDegree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            List<Integer> neibors = new ArrayList<>();
            adj.add(neibors);
        }

        for (int[] edge : prerequisites) {
            inDegree[edge[0]]++;
            adj.get(edge[1]).add(edge[0]);//后面的节点指向前面的
        }
        //有了图和入度
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int start = queue.poll();
            res.add(start);
            if (res.size() == numCourses) {
                int[] ans = new int[numCourses];
                for (int i = 0; i < numCourses; i++) {
                    ans[i] = res.get(i);
                }
                return ans;
            }
            for (int i : adj.get(start)) {
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    queue.add(i);
                }
            }
        }

        return new int[]{};
    }
}

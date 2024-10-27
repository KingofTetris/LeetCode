package LeetCode数据结构与算法基础.拓扑排序;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/10/27
 */
public class 课程表 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];//存每个结点的入度 为什么你能确定节点一定是numCourses个呢?
        List<List<Integer>> res = new ArrayList<>();//存结点之间依赖关系
        Queue<Integer> queue = new LinkedList<>();
        //初始化二维List集合
        for(int i = 0; i < numCourses; i++)
            res.add(new ArrayList<>());
        //取出每一对结点
        for(int[] temp : prerequisites){
            inDegree[temp[0]]++; //temp[0]入度++
            res.get(temp[1]).add(temp[0]); //temp[1] -> temp[0]
        }

        //图和入度建立后以后


        //先把所有入度为0的结点加入队列
        for(int i = 0; i < numCourses; i++)
            if(inDegree[i] == 0)
                queue.add(i);

        //记住拓扑排序 队列只存度为0的节点
        while(!queue.isEmpty()){
            //每个入度为0的出队一次，就选修了一门课程。
            int pre = queue.poll();
            numCourses--;
            //根据依赖关系，把对应的节点入度-1
            for(int cur : res.get(pre)){
                //如果cur入度等于0，入队。
                if(--inDegree[cur] == 0)
                    queue.add(cur);
            }
        }
        return numCourses == 0;
    }

}

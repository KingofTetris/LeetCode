package LeetCode数据结构与算法基础.DFS与BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个map。会给出你每个人不喜欢和谁呆在一起。
 * 问这群人 最后是否能分成2组。
 */
public class 人群分类 {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> dislikes = new HashMap<>();
        dislikes.put(1, List.of(2, 3));
        dislikes.put(2, List.of(1, 4));
        dislikes.put(3, List.of(1, 5));
        dislikes.put(4, List.of(2));
        dislikes.put(5, List.of(4));

        boolean canDivide = canDivideIntoTwoGroups(dislikes);
        System.out.println("能否将人分成两组: " + canDivide);
    }

    public static boolean canDivideIntoTwoGroups(Map<Integer, List<Integer>> dislikes) {
        int n = dislikes.size();
        int[] group = new int[n + 1]; // 用于标记每个人所属的组，0表示未分组，1和-1分别表示两个组
        for (int i = 1; i <= n; i++) {
            //默认给他分第一组。
            if (group[i] == 0 && !dfs(i, 1, group, dislikes)) {
                return false;
            }
        }
        return true;
    }

    public static boolean dfs(int person, int curGroup, int[] group, Map<Integer, List<Integer>> dislikes) {
        if (group[person] != 0) {
            return group[person] == curGroup;
        }

        group[person] = curGroup;
        int nextGroup = curGroup == 1 ? -1 : 1;

        for (int dis : dislikes.getOrDefault(person, new ArrayList<>())) {
            if (!dfs(dis, nextGroup, group, dislikes)) {
                return false;
            }
        }

        return true;
    }


}

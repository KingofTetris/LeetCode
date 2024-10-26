package LeetCode数据结构与算法基础.贪心;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/10/26
 */
public class 插入区间 {
    //就是合并区间的变种。从原来一个intervals，多了一个new
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0){
           int[][] res = new int[1][];
           res[0] = newInterval;
           return res;
        }
        int n = intervals.length;
        int[][] newIntervals = new int[n + 1][];
        for (int i = 0; i < n; i++) {
            newIntervals[i] = intervals[i];
        }
        newIntervals[n] = newInterval; //插入到新区间数组最后，然后排序
        //按左端点从小到大排序
        Arrays.sort(newIntervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> ans = new ArrayList<>();
        ans.add(newIntervals[0]);
        for (int i = 1; i < n + 1; i++) {
            int preRight = ans.get(ans.size() - 1)[1];
            if (newIntervals[i][0] <= preRight){
                ans.get(ans.size() - 1)[1] = Math.max(preRight,newIntervals[i][1]);
            }
            else {
                ans.add(newIntervals[i]);
            }
        }

        int[][] res = new int[ans.size()][];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}

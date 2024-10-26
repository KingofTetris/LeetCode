package LeetCode数据结构与算法基础.贪心;

import org.junit.Test;

import java.util.*;

/**
 * @author KingofTetris
 * @File 合并区间
 * @Time 2021/10/11  16:34
 */

/*56. 合并区间
        以数组 intervals 表示若干个区间的集合，其中单个区间为
        intervals[i] = [starti, endi] 。请你合并所有重叠的区间，
        并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

        示例 1：
        输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
        输出：[[1,6],[8,10],[15,18]]
        解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
        示例 2：
        输入：intervals = [[1,4],[4,5]]
        输出：[[1,5]]
        解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。


        提示：

        1 <= intervals.length <= 10^4
        intervals[i].length == 2
        0 <= starti <= endi <= 10^4
        */
public class 合并区间 {
    @Test
    public void test() {
        int[][] intervals = {{3, 4}, {1, 2}, {0, 8}, {-2, 4}, {9, 10}};
        //排序后 {{-2,4},{0,8},{1,2},{3,4},{9,10}}
        //只要前一个区间的右端点大于下一个区间的左端点，一定重叠。
        //然后取两者的min(l1,l2) max(r1,r2) 合并为新的区间。
        //当遇到下一个不重叠区间的时候，把cur = 这个不重叠的区间 重复这个过程
        //直到遍历完区间即可
        int[][] res = merge(intervals);
        for (int[] item : res) {
            for (int i = 0; i < item.length; i++) {
                System.out.print(item[i] + "\t");
            }
            System.out.println();
        }
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (p, q) -> p[0] - q[0] ); // 按照左端点从小到大排序
        LinkedList<int[]> ans = new LinkedList<>();

        ans.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            //如果重叠，左端点不用变，右端点变成更大的那个
            int preRight = ans.get(ans.size() - 1)[1];
            if (intervals[i][0] <= preRight){
                //比较当前区间的右端点和前一个重合区间的右端点哪个更大
                ans.get(ans.size() - 1)[1] = Math.max(intervals[i][1],preRight);
            }
            //如果不重叠，那么就添加这个新的区间
            else {
                ans.add(intervals[i]);
            }
        }
        //最后List转化为二维数组即可。
        // 将List<int[]>转化为二维数组
        int[][] result = new int[ans.size()][];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        return result;
    }
}

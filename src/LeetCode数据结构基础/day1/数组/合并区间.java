package LeetCode数据结构基础.day1.数组;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
        //直到遍历完区间即可，为什么下面要写递归？
        int[][] res = merge(intervals);
        for (int[] item : res) {
            for (int i = 0; i < item.length; i++) {
                System.out.print(item[i] + "\t");
            }
            System.out.println();
        }
    }


    //二维数组其实是一位数组的嵌套（每一行看做一个内层的一维数组）
    //[[0,1,2],[3,4,5],[6,7,8]]
    //其实就是这个意思 看着是3*3的矩阵 实际上是一个数组嵌套3个小数组
    //所以它下面才比较的是int[] 而不是int值
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        //策略模式传入Comparator比较器，根据Compare方法 自定义排序
        //按照左端点升序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> merged = new ArrayList<>();
        //intervals.length是行数，其实就有多少个区间
        for (int i = 0; i < intervals.length; ++i) {
            //每个区间的左右端点
            int L = intervals[i][0], R = intervals[i][1];
            //列表和数组同样都是从0计数
            //merged.get(merged.size() - 1)[1]其实就是前一个区间的右端点
            //merged.size == 0当然要加入一个区间才能对比
            //当merged中的区间右端点小于当前区间的左端点时，那肯定不重合
            //直接把当前区间加入merged中
            //因为按左端点排过序
            //这个时候就比较谁的R大，就把大的R赋给merged.get(merged.size() - 1)[1]
            //重复这个过程到结束就行。
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        //最后要把list转化成int[][]
        //方法是merged.toArray(new int[merged.size()][])
        //指定个行数就行。
        return merged.toArray(new int[merged.size()][]);
    }
}

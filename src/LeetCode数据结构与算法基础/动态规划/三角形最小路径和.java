package LeetCode数据结构与算法基础.动态规划;

import java.util.List;

/**
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点
 * 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 *
 * 提示：
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *
 * 如果将每一行的左端对齐，那么会形成一个等腰直角三角形，如下所示：
 *
 * [2]
 * [3,4]
 * [6,5,7]
 * [4,1,8,3]
 *
 **/

/**
 * 本题是一道非常经典且历史悠久的动态规划题，其作为算法题出现，最早可以追溯到 1994 年的 IOI（国际信息学奥林匹克竞赛）
 * 如今已经30年了。往日的国际竞赛题如今已经变成了动态规划的入门必做题，不断督促着我们学习和巩固算法。
 * 旧时王谢堂前燕，飞入寻常百姓家
 */
public class 三角形最小路径和 {

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();//三角形的行数
        int[][] f = new int[n][n]; //dp数组 f[i][j] 表示从三角形顶部走到位置 (i,j) 的最小路径和。
        f[0][0] = triangle.get(0).get(0); //初始状态
        //dp状态转移公式
        //i,j只能从上一步(i-1,j) (i-1,j-1)走过来
        //f[i][j]=min(f[i−1][j−1],f[i−1][j])+c[i][j]
        //因为是减法递推，i,j就得从1开始
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
        }
        //开始递推
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < i; ++j) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            //还有个体例，最右侧的值只能从上一行的最右侧移动过来。
            //不需要去比较min
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }
        //最后去找到最后一行的最小路线就可以了。
        int minTotal = f[n - 1][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[n - 1][i]);
        }
        return minTotal;
    }
}
/*
作者：力扣官方题解
链接：https://leetcode.cn/problems/triangle/solutions/329143/san-jiao-xing-zui-xiao-lu-jing-he-by-leetcode-solu/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/

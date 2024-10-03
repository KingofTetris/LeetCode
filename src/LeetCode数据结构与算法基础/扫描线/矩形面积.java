package LeetCode数据结构与算法基础.扫描线;

/**
 * @author by KingOfTetris
 * @date 2024/10/3
 */

/**
 给你 二维 平面上两个 由直线构成且边与坐标轴平行/垂直 的矩形，请你计算并返回两个矩形覆盖的总面积。

 每个矩形由其 左下 顶点和 右上 顶点坐标表示：

 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。


 示例 1：

 Rectangle Area
 输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
 输出：45
 示例 2：

 输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
 输出：16


 提示：

 -104 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 104
 https://leetcode.cn/problems/rectangle-area/description/
 */
public class 矩形面积 {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        /**
         * 其实根本没必要考虑那么复杂
         * 反正你不重叠就让squre返回0
         * 重叠去算就行了
         */
        //画个四个点不重叠的 看着图求交叉部分的边
        int lx = Math.max(ax1, bx1); //leftX
        int rx = Math.min(ax2, bx2); // rightX

        int by = Math.max(ay1, by1);//bottomY
        int ty = Math.min(ay2, by2);//topY

        //算各自的面积?
        int s1 = square(ax1, ay1, ax2, ay2);
        int s2 = square(bx1, by1, bx2, by2);
        //然后算重叠部分的面积
        int s3 = square(lx, by, rx, ty);
        //相加-一次重叠就是两个部门的总面积
        //如果只是要求重叠面积，那就是s3。
        return s1 + s2 - s3;
    }

    private int square(int x1, int y1, int x2, int y2) {
        if (x1 >= x2 || y1 >= y2) return 0;//非法坐标。
        return (y2 - y1) * (x2 - x1);
    }
}

package 剑指offer第二版.数组_矩阵;

/**
 * @Author KingofTetris
 * @Date 2022/7/6 15:57
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 *  
 *
 * 限制：
 *
 * 0 <= n <= 1000
 *
 * 0 <= m <= 1000
 *
 *
 */
public class 剑指Offer04_二维数组中的查找 {
    /**
     * 思路：https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/solution/mian-shi-ti-04-er-wei-shu-zu-zhong-de-cha-zhao-zuo/
     * @param matrix
     * @param target
     * @return
     * 时间复杂度 O(m+n) 其中，N 和 M 分别为矩阵行数和列数，此算法最多循环 M+N 次
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = matrix.length - 1;//行数
        int j = 0; //[i][0] 一开始是整个矩阵的左下角的标志数 随着i j的变化，改变标志数，但始终是左下角的数
        while (i>=0 && j<matrix[0].length){ //保证数组不越界
            if (matrix[i][j] > target) i--; //如果比标志数小，那就往上找就是i--
            else if (matrix[i][j] < target) j++; //如果比标志数大那么只能是在右边，就是j++
            else return true;
        }
        return false;
    }

    /**
     *暴力法 按顺序遍历显示不是好方法。 O(m*n)
     * @param matrix
     * @param target
     * @return
     */

}

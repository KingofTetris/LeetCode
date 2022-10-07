package 程序员面试金典;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/27 16:57
 */
public class _01_08_零矩阵 {

    @Test
    public void test(){
        int[][] ints = {
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        };

        setZeroes(ints);

        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }


    /**
     * 方法一：使用标记数组
     * 思路和算法
     *
     * 我们可以用两个标记数组分别记录每一行和每一列是否有零出现。
     *
     * 具体地，我们首先遍历该数组一次，如果某个元素为 0，
     * 那么就将该元素所在的行和列所对应标记数组的位置置为 true。
     * 最后我们再次遍历该数组，用标记数组更新原数组即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/zero-matrix-lcci/solution/ling-ju-zhen-by-leetcode-solution-7ogg/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     *
     * 优化到常数空间
     * @param matrix
     */
    public void setZeroes3(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean row0_flag = false;
        boolean col0_flag = false;
        // 记录第一行原本是否有零
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                row0_flag = true;
                break;
            }
        }
        // 记录第一列是否有零
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                col0_flag = true;
                break;
            }
        }
        // 把第一行第一列作为标记 相当于第一题的二维数组
        // 那么从[1][1]开始 到 [row-1] [col-1]
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        // 用标记位置0 只要你在为0的行列上，那么你就为0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) { //如果标记位行列有一个是0，那么就把这个数置为0
                    matrix[i][j] = 0;
                }
            }
        }


        //最后根据两个flag判断要不要把第一行第一列置为0
        //如果原本有0.全部置0，如果没有0，就不用管了。
        if (row0_flag) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col0_flag) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }

       /* 作者：powcai
        链接：https://leetcode.cn/problems/set-matrix-zeroes/solution/o1kong-jian-by-powcai/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
/*
    作者：LeetCode-Solution
    链接：https://leetcode.cn/problems/zero-matrix-lcci/solution/ling-ju-zhen-by-leetcode-solution-7ogg/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int k = 0;
        boolean[][] flags = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0 && !flags[i][j]){ //原本为0才去变化
                    while (k < n ){ //列不变，行变0
                        if (matrix[k][j] == 0) {
                            k++;
                            continue;//本来就是0 跳过
                        }
                        matrix[k][j] = 0;
                        flags[k][j] = true;//标记为 人为添加的0
                        k++;
                    }
                    k = 0;
                    while (k < m){ //行不变，列变0
                        if (matrix[i][k] == 0) {
                            k++;
                            continue;//本来就是0 跳过
                        }
                        matrix[i][k] = 0;
                        flags[i][k] = true;//标记为 人为添加的0
                        k++;
                    }
                    k = 0;
                }
            }
        }
    }
}

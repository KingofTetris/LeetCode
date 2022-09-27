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

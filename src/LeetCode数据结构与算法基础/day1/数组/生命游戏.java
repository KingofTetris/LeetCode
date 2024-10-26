package LeetCode数据结构与算法基础.day1.数组;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/10/26
 */
public class 生命游戏 {

    public void gameOfLife(int[][] board) {
        //1.复制数组
        int n = board.length;
        int m = board[0].length;
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = board[i][j];
            }
        }

        //计算每个细胞周围的活细胞数量
        int aliveCellCount;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                aliveCellCount = 0; //每个细胞周围的活细胞数量
                //行和列的范围是i-1到i+1,j-1到j+1
                //这个遍历范围就是[i-1][j-1] 到[i+1][j+1]
                //包括了八个方向
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        //判断k和l的范围是否合法，如果合法，判断copy数组的元素是否为1，如果是liveCount++
                        //去掉他自己 [i,j]
                        if (k >= 0 && k < n && l >= 0 && l < m && !(k == i && l == j)) {
                            if (copy[k][l] == 1) {
                                aliveCellCount++;
                            }
                        }
                    }
                }

                //根据活细胞的数量，更新board数组
                //死亡
                if (copy[i][j] == 1) {
                    if (aliveCellCount < 2 || aliveCellCount > 3) {
                        board[i][j] = 0;
                    }
                }
                //复活
                if (copy[i][j] == 0) {
                    if (aliveCellCount == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }
}

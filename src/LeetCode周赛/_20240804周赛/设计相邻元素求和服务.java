package LeetCode周赛._20240804周赛;

/**
 * @author by KingOfTetris
 * @date 2024/8/11
 */
public class 设计相邻元素求和服务 {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}
        };

        NeighborSum obj = new NeighborSum(grid);
        int param_1 = obj.adjacentSum(4);
        int param_2 = obj.diagonalSum(8);
        System.out.println(param_1 + " " + param_2);
    }
}

class NeighborSum {

    int[][] orGrid;

    public NeighborSum(int[][] grid) {
        // n * n
        orGrid = new int[grid.length][grid.length];
        // 首先初始化矩阵
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                orGrid[i][j] = grid[i][j];
            }
    }

    public int adjacentSum(int value) {
        // 计算value的相邻元素之和 上下左右
        // 首先要定位value
        int x = -1;
        int y = -1;
        for (int i = 0; i < orGrid.length; i++)
            for (int j = 0; j < orGrid[0].length; j++) {
                if (orGrid[i][j] == value) {
                    x = i;
                    y = j;
                }
            }
        // 然后计算
        int sum = 0;
        int top = x - 1, bottom = x + 1, left = y - 1, right = y + 1;
        if (top >= 0)
            sum += orGrid[top][y];
        if (bottom < orGrid.length)
            sum += orGrid[bottom][y];
        if (left >= 0)
            sum += orGrid[x][left];
        if (right < orGrid[0].length)
            sum += orGrid[x][right];

        return sum;
    }

    public int diagonalSum(int value) {
        // 计算value的相邻元素之和 上下左右
        // 首先要定位value
        int x = -1;
        int y = -1;
        for (int i = 0; i < orGrid.length; i++)
            for (int j = 0; j < orGrid[0].length; j++) {
                if (orGrid[i][j] == value) {
                    x = i;
                    y = j;
                }
            }
        // 然后计算
        int sum = 0;
        int xTL = x - 1, yTL = y - 1;
        int xTR = x - 1, yTR = y + 1;
        int xBL = x + 1, yBL = y - 1;
        int xBR = x + 1, yBR = y + 1;

        // 左上
        if (xTL >= 0 && yTL >= 0)
            sum += orGrid[xTL][yTL];
        // 右上
        if (xTR >= 0 && yTR < orGrid[0].length)
            sum += orGrid[xTR][yTR];
        // 左下
        if (xBL < orGrid.length && yBL >= 0)
            sum += orGrid[xBL][yBL];
        // 右下
        // 这里为什么ArrOOBE
        if (xBR < orGrid.length && yBR < orGrid[0].length)
            sum += orGrid[xBR][yBR];

        return sum;
    }
}

/**
 * Your NeighborSum object will be instantiated and called as such:
 * NeighborSum obj = new NeighborSum(grid);
 * int param_1 = obj.adjacentSum(value);
 * int param_2 = obj.diagonalSum(value);
 */

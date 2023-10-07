package LeetCode数据结构基础.DFS与BFS;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 岛屿的最大面积
 * @Time 2021/9/25  14:43
 */
/*695. 岛屿的最大面积
        给你一个大小为 m x n 的二进制矩阵 grid 。
        岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在
         水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
        岛屿的面积是岛上值为 1 的单元格的数目。
        计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
        示例 1：
        输入：grid=[ [0,0,1,0,0,0,0,1,0,0,0,0,0],
                    [0,0,0,0,0,0,0,1,1,1,0,0,0],
                    [0,1,1,0,1,0,0,0,0,0,0,0,0],
                    [0,1,0,0,1,1,0,0,1,0,1,0,0],
                    [0,1,0,0,1,1,0,0,1,1,1,0,0],
                    [0,0,0,0,0,0,0,0,0,0,1,0,0],
                    [0,0,0,0,0,0,0,1,1,1,0,0,0],
                    [0,0,0,0,0,0,0,1,1,0,0,0,0]]
        输出：6
        解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
        示例 2：

        输入：grid = [[0,0,0,0,0,0,0,0]]
        输出：0


        提示：

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 50
        grid[i][j] 为 0 或 1*/

    //最方便的方法是把访问过的土地直接置为0，为0就不进行DFS
    //但是对原数组进行了修改不是很好。
    //可以加个visited[][]数组，把访问过的1直接添加进去 赋值为1表示已经访问过
    //这个visited数组只能按题目 限制直接给出，不然没法赋值；1 <= m, n <= 50
    //不过这种方法在不改变原数组的情况下，时空效率都很低
public class 岛屿的最大面积 {
    int[] dx = {0,0,-1,1};
    int[] dy = {1,-1,0,0};
    int[][] visited = new int[50][50];//初始化后是50 x 50的全0数组
    @Test
    public void test(){
        int[][] grid={{0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(maxAreaOfIsland(grid));
    }
    public int maxAreaOfIsland(int[][] grid) {
        int areaMax = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //既然做了visited数组,0也直接跳过
                if(visited[i][j] == 1 || grid[i][j] == 0)
                    continue;  //跳过本次循环
                //遍历到大的就更新
                if (grid[i][j] == 1 )
                areaMax = Math.max(areaMax,DFS(grid,i,j));
            }
        }
        return areaMax;
    }

    //设置x y是遍历起点
    public int DFS(int[][] grid,int x,int y){
        //遍历算法上来先判断边界
        //超过矩阵边界的，格子里是水的都不可能是岛屿，访问过的也不再继续探索
       if( x < 0 || y <0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0 || visited[x][y] == 1)
           return 0;
       //是陆地先标记 areaNow = 1
        //一直初始化visited数组会溢出！一开始就要先定好
//       visited = new int[grid.length][grid[0].length];
        //法一 就是下面这个数组
       visited[x][y] = 1;
//        法二 改变原数组，直接把访问过的置为0
//       grid[x][y] = 0;
        //递归里的变量即使重名也不会覆盖
       int areaNow = 1;
       //开始上下左右探索
        for (int i = 0; i < 4; i++) {
            int mx = x + dx[i];
            int my = y + dy[i];
            areaNow = areaNow + DFS(grid,mx,my);
        }
        return areaNow;
    }

}

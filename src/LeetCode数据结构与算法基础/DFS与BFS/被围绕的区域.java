package LeetCode数据结构与算法基础.DFS与BFS;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/8/24
 */
public class 被围绕的区域 {
    @Test
    public void test() {
       /* char[][] grid = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};*/
        char[][] grid = {
                {'O','O','O'},
                {'O','O','O'},
                {'O','O','O'},
                {'O','O','O'}
        };
       /* char[][] grid = {
                {'X', 'O', 'X'},
                {'X', 'O', 'X'},
                {'X', 'O', 'X'}};*/
        /*char[][] grid = {
                {'O', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'O', 'O'},
                {'X', 'X', 'O', 'X', 'O'}};*/

        solve(grid);
        for (char[] chars : grid) {
            System.out.println(chars);
        }
    }

    /**
     * 题意就是把  所有被X围绕的'O' 全部改为X
     * 如果这个'O'在边界上，就不能被捕获
     * <p>
     * 解题思路的话不是真的去找哪个'O'被包围，哪个'O'没有被包围，而是
     * 去找那些和边缘'O'相连接的'O'，这些'O'就不会被包围。 我们把这些'O'标记起来
     * <p>
     * 而对于那些没有被标记的'O'，就是需要回收的'O'
     * <p>
     * 最后遍历数组把需要回收的0改为'X'即可。
     * <p>
     * 下面是用'A'来进行标记
     *
     * @param board
     */
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    boolean[][] used;
    int n, m;

    public void solve(char[][] board) {
        if (board == null) return;
        n = board.length;
        m = board[0].length;
        used = new boolean[n][m];
        //我们只去找边缘的'O',从他出发
        //边缘节点
        // 上[0,0]->[0,m-1]
        for (int i = 0; i <= m - 1; i++) {
            //如果是没用过的'O'
            if (board[0][i] == 'O' && !used[0][i]) dfs(board, 0, i);
        }
        //下[n-1,0]->[n-1,m-1]
        for (int i = 0; i <= m - 1; i++) {
            if (board[n - 1][i] == 'O' && !used[n - 1][i]) dfs(board, n - 1, i);
        }
        //左 [0,0] -> [n-1,0]
        for (int i = 0; i <= n - 1; i++) {
            if (board[i][0] == 'O' && !used[i][0]) dfs(board, i, 0);
        }
        //右 [0,m-1]->[n-1,m-1]
        for (int i = 0; i <= n - 1; i++) {
            if (board[i][m - 1] == 'O' && !used[i][m - 1]) dfs(board, i, m - 1);
        }

        //标记完成以后，再次遍历board把'A' 改成 'X' 即可
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //标记了的还是O
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
                //没标记的改成X
                //注意这里不能直接用if，因为上面已经把他改成'O'了
                //board[i][j] == 'O' 他一定成立 所以要用else
                //或者你先判断是不是'O'
                else if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }

    }

    //为什么使用为什么的全局n,m 会是0 我淦??
    public void dfs(char[][] board, int x, int y) {
        //标记
        used[x][y] = true;
        board[x][y] = 'A';
        for (int k = 0; k < 4; k++) {
            int i = x + dx[k];
            int j = y + dy[k];
            if (i < 0 || i >= n || j < 0 || j >= m){
                continue;
            }
            if (board[i][j] == 'O' && used[i][j] == false){
                dfs(board,i,j);
            }
        }
    }

  /*  作者：力扣官方题解
    链接：https://leetcode.cn/problems/surrounded-regions/solutions/369110/bei-wei-rao-de-qu-yu-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}

package 校招笔试真题.得物._20240903;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/5
 */

//TODO 为什么会死循环。可能不是死循环，就是要跑那么久。
public class 小塔的逃跑计划 {
    //从起点开始到终点最少花费，若无法到底返回-1
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    static int[] start = new int[2];
    static int[] end = new int[2];

    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] mat = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String row = sc.next();
            char[] chars = row.toCharArray();
            for (int j = 0; j < m; j++) {
                mat[i][j] = chars[j];
                if (chars[j] == 'B') {
                    start[0] = i;
                    start[1] = j;
                    visited[i][j] = true;//起点一开始就是已访问，防止成环?
                }
                if (chars[j] == '*') {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }

        dfs(mat, start[0], start[1], 3, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void dfs(char[][] mat, int x, int y, int usedBoom, int cost) {
        //如果走到终点就可以返回了。
        if (x == end[0] && y == end[1]) {
            //因为到终点的这一步没有计算，直接+1
            ans = Math.min(ans, cost + 1);
            return;
        }
        if (mat[x][y] == '.') cost++;
        if (mat[x][y] == 'W') {
            //没炸弹了，此路不通，return
            if (usedBoom <= 0) return;
            else {
                //否则的话，计算消耗，炸弹-1
                cost += 2;
                usedBoom--;
            }
        }
        //剪枝，如果消耗已经超过当前ans了，就没必要再往下走了。
        if (cost >= ans) {
            return;
        }
        //从x,y向外扩张去找end[i],end[j]
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                continue;//如果越界，就换一下步
            }
            //如果已经走过了，也别访问了，防止成环。
            if (visited[nextX][nextY]) continue;
            //其他情况
            visited[nextX][nextY] = true;
            dfs(mat, nextX, nextY, usedBoom, cost);
            //回溯，因为其他的路也有可能走这个方向过来，得回溯改成false
            visited[nextX][nextY] = false;
        }
    }
}

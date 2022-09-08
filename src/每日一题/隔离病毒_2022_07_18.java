package 每日一题;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2022/7/18
 * 病毒扩散得很快，现在你的任务是尽可能地通过安装防火墙来隔离病毒。
 *
 * 假设世界由 m x n 的二维矩阵 isInfected 组成， isInfected[i][j] == 0 表示该区域未感染病毒，而  isInfected[i][j] == 1 表示该区域已感染病毒。可以在任意 2 个相邻单元之间的共享边界上安装一个防火墙（并且只有一个防火墙）。
 *
 * 每天晚上，病毒会从被感染区域向相邻未感染区域扩散，除非被防火墙隔离。现由于资源有限，每天你只能安装一系列防火墙来隔离其中一个被病毒感染的区域（一个区域或连续的一片区域），且该感染区域对未感染区域的威胁最大且 保证唯一 。
 *
 * 你需要努力使得最后有部分区域不被病毒感染，如果可以成功，那么返回需要使用的防火墙个数; 如果无法实现，则返回在世界被病毒全部感染时已安装的防火墙个数。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入: isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
 * 输出: 10
 * 解释:一共有两块被病毒感染的区域。
 * 在第一天，添加 5 墙隔离病毒区域的左侧。病毒传播后的状态是:
 *
 * 第二天，在右侧添加 5 个墙来隔离病毒区域。此时病毒已经被完全控制住了。
 *
 * 示例 2：
 *
 *
 *
 * 输入: isInfected = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出: 4
 * 解释: 虽然只保存了一个小区域，但却有四面墙。
 * 注意，防火墙只建立在两个不同区域的共享边界上。
 * 示例 3:
 *
 * 输入: isInfected = [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
 * 输出: 13
 * 解释: 在隔离右边感染区域后，隔离左边病毒区域只需要 2 个防火墙。
 *  
 *
 * 提示:
 *
 * m == isInfected.length
 * n == isInfected[i].length
 * 1 <= m, n <= 50
 * isInfected[i][j] is either 0 or 1
 * 在整个描述的过程中，总有一个相邻的病毒区域，它将在下一轮 严格地感染更多未受污染的方块 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/contain-virus
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 隔离病毒_2022_07_18 {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int containVirus(int[][] isInfected) {
        int m = isInfected.length, n = isInfected[0].length;
        int ans = 0;
        while (true) {
            List<Set<Integer>> neighbors = new ArrayList<>();
            List<Integer> firewalls = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (isInfected[i][j] == 1) {
                        Queue<int[]> queue = new ArrayDeque<>();
                        queue.offer(new int[]{i, j});
                        Set<Integer> neighbor = new HashSet<>();
                        int firewall = 0, idx = neighbors.size() + 1;
                        isInfected[i][j] = -idx;

                        while (!queue.isEmpty()) {
                            int[] arr = queue.poll();
                            int x = arr[0], y = arr[1];
                            for (int d = 0; d < 4; ++d) {
                                int nx = x + dirs[d][0], ny = y + dirs[d][1];
                                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                    if (isInfected[nx][ny] == 1) {
                                        queue.offer(new int[]{nx, ny});
                                        isInfected[nx][ny] = -idx;
                                    } else if (isInfected[nx][ny] == 0) {
                                        ++firewall;
                                        neighbor.add(getHash(nx, ny));
                                    }
                                }
                            }
                        }
                        neighbors.add(neighbor);
                        firewalls.add(firewall);
                    }
                }
            }

            if (neighbors.isEmpty()) {
                break;
            }

            int idx = 0;
            for (int i = 1; i < neighbors.size(); ++i) {
                if (neighbors.get(i).size() > neighbors.get(idx).size()) {
                    idx = i;
                }
            }
            ans += firewalls.get(idx);
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (isInfected[i][j] < 0) {
                        if (isInfected[i][j] != -idx - 1) {
                            isInfected[i][j] = 1;
                        } else {
                            isInfected[i][j] = 2;
                        }
                    }
                }
            }
            for (int i = 0; i < neighbors.size(); ++i) {
                if (i != idx) {
                    for (int val : neighbors.get(i)) {
                        int x = val >> 16, y = val & ((1 << 16) - 1);
                        isInfected[x][y] = 1;
                    }
                }
            }
            if (neighbors.size() == 1) {
                break;
            }
        }
        return ans;
    }

    public int getHash(int x, int y) {
        return (x << 16) ^ y;
    }


/*    作者：LeetCode-Solution
    链接：https://leetcode.cn/problems/contain-virus/solution/ge-chi-bing-du-by-leetcode-solution-vn9m/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}

/**
 * 击败双百
 * 根据题意，我们需要先找到感染面积最大的区域进行隔离，将思路分成三个步骤
 *
 * ①获取最大的感染面积的区域和所需要的防火墙数量
 * ②将最大的感染面积的区域的病毒设置为死亡状态
 * ③存活的病毒进行扩散处理
 *
 * 作者：jiang-hui-4
 * 链接：https://leetcode.cn/problems/contain-virus/solution/ge-chi-bing-du-by-jiang-hui-4-21s8/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution{
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int curWall = 0;
    int rows;
    int cols;

    public int containVirus(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int result = 0;
        while (true) {
            int walls = getMaxAreaNeedWalls(grid);
            if (walls == 0) { //没法设防了就跳出。
                break;
            }
            result += walls;
        }
        return result;
    }

    private int getMaxAreaNeedWalls(int[][] grid) {
        //maxArea代表最大的感染区,ans代表需要修的墙的数量,
        //targetX、targetY表示此区域的DFS开始的坐标
        //state表示当前区域要修建墙的状态,如果没有修过,需要给maxArea+1,修过的话，只加墙的数量,不加maxArea
        //并且每个区域的state都是不一样的，互相不能影响 DFS完给state-1
        int maxArea = 0, ans = 0, targetX = -1, targetY = -1, state = -3;
        //存放访问的状态
        int[][] visited = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //找到没有访问的病毒区域
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    //当前区域需要的防火墙数量
                    curWall = 0;
                    //当前区域能感染的数量
                    int curMaxArea = dfs(grid, visited, i, j, state);
                    if (curMaxArea > maxArea) {
                        maxArea = curMaxArea;
                        ans = curWall;
                        targetX = i;
                        targetY = j;
                    }
                    state--;
                }
            }
        }
        if (targetX == -1) {
            return 0;
        }
        //将活跃的病毒改为死亡状态
        modifyDead(grid, targetX, targetY);
        visited = new int[rows][cols];
        //病毒扩散
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    spread(grid, visited, i, j);
                }
            }
        }
        return ans;
    }

    private void spread(int[][] grid, int[][] visited, int x, int y) {
        visited[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int newX = x + dirs[i][0];
            int newY = y + dirs[i][1];
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols
                    && visited[newX][newY] == 0) {
                //扩散区域
                if (grid[newX][newY] == 0) {
                    grid[newX][newY] = 1;
                    visited[newX][newY] = 1;
                } else if (grid[newX][newY] == 1) {
                    spread(grid, visited, newX, newY);
                }
            }
        }
    }

    private void modifyDead(int[][] grid, int x, int y) {
        grid[x][y] = -2;
        for (int i = 0; i < 4; i++) {
            int newX = x + dirs[i][0];
            int newY = y + dirs[i][1];
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 1) {
                modifyDead(grid, newX, newY);
            }
        }
    }

    private int dfs(int[][] grid, int[][] visited, int x, int y, int state) {
        int curArea = 0;
        visited[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int newX = x + dirs[i][0];
            int newY = y + dirs[i][1];
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && visited[newX][newY] != 1) {
                //不是病毒
                if (grid[newX][newY] == 0) {
                    curWall++;
                    //是否已经访问，是的话只加墙，不是需要加墙和区域（一个区域有四个墙）
                    if (visited[newX][newY] != state) {
                        curArea++;
                        visited[newX][newY] = state;
                    }
                } else if (grid[newX][newY] == 1) {
                    //是存活病毒
                    curArea += dfs(grid, visited, newX, newY, state);
                }
            }
        }
        return curArea;
    }

}


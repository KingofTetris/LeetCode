package LeetCode数据结构与算法基础.DFS与BFS;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/9/7
 */


/**
 * 从米哈游_20240907 的 米小游与流萤  过来的
 *
 * 这道最大人工岛 就是这道笔试题的原题相当与。
 *
 * 唉，代码随想录全部都有，你是一题不做啊。
 */
public class 最大人工岛 {

    // 定义二维数组表示四个方位
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, 1, -1, 0};

    //给每块陆地和海洋标记编号
    static int[][] idGrid;
    //两种区域的ID,原本是需要两种区域的ID的，但是这里海洋不能扩张，只能点一下
    //那么其实只要统计landId就行了，最后相邻大陆的区域面积+1即可。
    static int landId = 1, oceanId = -1;

    //两种区域的大小
    static HashMap<Integer, Integer> areaMap = new HashMap<>();

    //给每个水域标记邻居陆地有哪些
    static HashMap<Integer, Set<Integer>> neighbor = new HashMap<>();

    public static void main(String[] args) {
        // 接收输入
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        idGrid = new int[n][m];

        //统计陆地信息
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //如果是新陆地
                if (grid[i][j] == 1) {
                    int size = dfsLand(grid, i, j, landId);
                    areaMap.put(landId, size);
                    landId++;
                }
            }
        }
        int landSum = 0;
        for (Integer value : areaMap.values()) {
            landSum += value;
        }
        //如果全是陆地，直接返回陆地大小就行了，不用往下走了
        if (landSum == n * m) {
            System.out.println(landSum);
            return;
        }

        //统计水域信息
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //如果是新海洋，因为这里只能点一块海域，没有必要DFS，
                //你只需要统计这块海洋附件有哪些陆地就行了
                if (grid[i][j] == 0) {
                    //把每一块海洋都看作是一个新的水域
                    HashSet<Integer> set = new HashSet<>();
                    //去遍历这块海洋的上下左右有没有陆地
                    //要注意已经把陆地改成'L'了。
                    //总之四个方向。如果他会扩展的话，这里是第二个DFS了。
                    if (i - 1 >= 0 && grid[i - 1][j] == 'L') {
                        set.add(idGrid[i - 1][j]);
                    }
                    if (i + 1 < n && grid[i + 1][j] == 'L') {
                        set.add(idGrid[i + 1][j]);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 'L') {
                        set.add(idGrid[i][j - 1]);
                    }
                    if (j + 1 < m && grid[i][j + 1] == 'L') {
                        set.add(idGrid[i][j + 1]);
                    }
                    //最后添加到neighbor,areaMap中
                    areaMap.put(oceanId, 1);
                    neighbor.put(oceanId, set);
                    oceanId--;
                }
            }
        }

        //开始求最大连接
        int max = 0;
        for (Map.Entry<Integer, Integer> area : areaMap.entrySet()) {
            //如果是陆地不用管他
            if (area.getKey() > 0) continue;
            int curOceanId = area.getKey();
            Set<Integer> set = neighbor.get(curOceanId);
            //如果这片海洋没有陆地邻居也不用管他。
            if (set == null) {
                continue;
            }
            int areaMax = 1;//把他点成陆地面积就是1
            //如果不为空，就要找一个点，他的相邻陆地最多。
            for (int curLandId : set) {
                areaMax += areaMap.get(curLandId);
            }
            max = Math.max(max, areaMax);
        }

        //如果max为0说明根本没有陆地，全是水，直接返回1
        int res = max == 0 ? 1 : max;
        System.out.println(res);
    }

    private static int dfsLand(int[][] grid, int i, int j, int landId) {
        grid[i][j] = 'L';//先标记为旧大陆
        idGrid[i][j] = landId;//标记这块陆地属于谁
        int n = grid.length;
        int m = grid[0].length;
        int size = 1;

        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == 1) {
                //size扩张
                size += dfsLand(grid, x, y, landId);
            }
        }

        return size;
    }
}

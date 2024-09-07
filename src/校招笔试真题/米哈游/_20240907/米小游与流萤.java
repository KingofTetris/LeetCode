package 校招笔试真题.米哈游._20240907;

import java.util.*;


/**
 * 最大人工岛的进阶版本。
 * <p>
 * 还是先去找陆地，对陆地进行编号。
 * 然后去尝试让海洋变成陆地，
 */
public class 米小游与流萤 {
    //分块，分为海洋和陆地，对每块陆地都编号一下
    //对每个海洋块编号，且对与其相邻的陆地进行记录
    //让每个海洋块变成陆地，求一个最大值
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, 1, -1, 0};
    static int[][] idGrid;

    //记录陆地的ID 和 面积
    static HashMap<Integer, Integer> id2area = new HashMap<>();

    //记录每块海洋相邻的陆地ID
    static HashMap<Integer, Set<Integer>> neighbour = new HashMap<>();


    //一正一负统计 就不会重合了
    static int LuDiId = 1, HaiYangId = -1;

    public static void main(String[] args) {
        //处理输入
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        in.nextLine();
        idGrid = new int[n][m];
        char[][] grid = new char[n][m];
        for (int i = 0; i < grid.length; i++) {
            String tmp = in.nextLine();
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = tmp.charAt(j);
            }
        }


        //统计陆地
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //如果是新大陆
                //用visited也可以，只是这个方法里面把旧大陆都标成L
                if (grid[i][j] == '#') {
                    int area = dfs(grid, i, j, LuDiId);
                    id2area.put(LuDiId, area);
                    LuDiId++;
                }
            }
        }

        //统计海洋
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //如果是新水域
                //用visited也可以，只是这个方法里面把旧水域都标成H
                if (grid[i][j] == '.') {
                    //给每片海洋添加相邻的大陆集合
                    HashSet<Integer> set = new HashSet<>();
                    neighbour.put(HaiYangId, set);
                    //计算海洋面积
                    int area = dfs2(grid, i, j, HaiYangId);
                    id2area.put(HaiYangId, area);
                    //海洋ID --
                    HaiYangId--;
                }
            }
        }

        //算最大的
        int max = 0;
        for (Map.Entry<Integer, Integer> map : id2area.entrySet()) {
            //如果是陆地就不管
            if (map.getKey() > 0) continue;
            //如果是海洋。
            Integer id = map.getKey();
            //获得海洋的大小
            Integer area = map.getValue();
            //获得这片海洋的陆地邻居
            Set<Integer> set = neighbour.get(id);
            //如果没有陆地，那说明全是海洋，那就不用麻烦了
            if (set == null) {
                //一烧起来全部变成海洋，为什么是1
                System.out.println(1);
            }
            //如果不为空，就尝试烧掉这篇海洋
            //把他的相邻陆地全部连起来
            for (int GirdId : set) {
                //海洋大小 + 相邻陆地大小
                area += id2area.get(GirdId);
            }
            max = Math.max(max, area);
        }
        System.out.println(max);
    }

    //计算陆地面积
    static int dfs(char[][] grid, int i, int j, int id) {
        idGrid[i][j] = id; //标记陆地编号
        grid[i][j] = 'L'; //把走过的大陆都标成L
        int size = 1;

        for (int k = 0; k < 4; k++) {
            int x = dx[k] + i;
            int y = dy[k] + j;
            //当合法，且是陆地。
            if (0 <= x && x < grid.length && 0 <= y && y < grid[0].length
                    && grid[x][y] == '#') {
                size += dfs(grid, x, y, id);
            }
        }
        return size;
    }


    static int dfs2(char[][] grid, int i, int j, int id) {
        idGrid[i][j] = id; //标记海洋所属ID
        grid[i][j] = 'H';
        //注意这个size。每走一步 return 一个size。 就相当于+1
        int size = 1;
        for (int k = 0; k < 4; k++) {
            int x = dx[k] + i;
            int y = dy[k] + j;
            if (0 <= x && x < grid.length && 0 <= y && y < grid[0].length) {
                //如果是水就继续往下走
                if (grid[x][y] == '.') {
                    size += dfs2(grid, x, y, id);
                }
                //如果是陆地就把相邻陆地ID 添加到neighbour集合中。
                //注意陆地已经被全部改成L了。
                else if (grid[x][y] == 'L') {
                    //把相邻陆地ID加到当前水域的陆地邻居集合里面。
                    Set<Integer> set = neighbour.get(id);
                    set.add(idGrid[x][y]);
                    neighbour.put(id, set);
                }
            }
        }
        return size;
    }

}

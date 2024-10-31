package LeetCode数据结构与算法基础.DFS与BFS;

public class ChessKnight {
    static int count = 0;
    static int[][] directions = {
            {-2, -1},
            {-2, 1},
            {-1, -2},
            {-1, 2},
            {1, -2},
            {1, 2},
            {2, -1},
            {2, 1}};

    public static void main(String[] args) {
        int rows = 16;
        int cols = 16;
        boolean[][] visited = new boolean[rows][cols];
        // 从(0, 0)位置开始搜索
        dfs(0, 0, visited, 0, rows, cols);
        System.out.println("最多有 " + count + " 种走法");
    }

    static void dfs(int x, int y, boolean[][] visited, int steps, int rows, int cols) {
        visited[x][y] = true;
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                dfs(newX, newY, visited, steps + 1, rows, cols);
            }
        }
        visited[x][y] = false; // 回溯
        count++; // 每次回溯说明找到了一种走法
    }
}

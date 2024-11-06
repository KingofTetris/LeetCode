package LeetCode数据结构与算法基础.手撕算法;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 就是一个传播过程，有点类似病毒传播那题。
 *
 * 广搜（BFS）
 *
 * 先遍历整个网格，将所有一开始就腐烂的橘子的位置加入队列。
 *
 * 使用一个队列来进行广度优先搜索。对于队列中的每个腐烂橘子，检查其四周的新鲜橘子并更新它们的状态为腐烂，
 * 同时将这些新鲜变腐的橘子的位置加入队列。
 *
 * 记录每轮操作的时间。当队列为空时，即没有更多新鲜橘子可以变腐，搜索结束。
 *
 * 是否还有新鲜的橘子。如果有，返回 -1；否则，返回记录的时间。
 *
 * 作者：Peter-Lu
 * 链接：https://leetcode.cn/problems/rotting-oranges/solutions/2739591/javaban-fu-lan-de-ju-zi-xiang-xi-de-zhu-2bjs7/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 */
public class 腐烂的橘子 {

    public int orangesRotting(int[][] grid) {
        int rows = grid.length; // 网格的行数
        int cols = grid[0].length; // 网格的列数
        Queue<int[]> queue = new LinkedList<>(); // 使用队列实现BFS，内容是坐标
        int freshCount = 0; // 新鲜橘子的数量

        // 遍历网格，初始化腐烂橘子的位置进队列，并计数新鲜橘子
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) { // 如果是腐烂的橘子
                    queue.offer(new int[]{r, c}); // 加入队列
                }
                else if (grid[r][c] == 1) { // 如果是新鲜的橘子
                    freshCount++; // 新鲜橘子计数增加
                }
            }
        }

        int minutesPassed = 0; // 经过的分钟数
        // 四个可能的移动方向：上、下、左、右
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 广搜 注意新鲜橘子freshCount需要>0
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size(); // 当前队列的大小，即这一分钟将要处理的腐烂橘子数
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll(); // 出队一个腐烂的橘子
                int r = point[0];
                int c = point[1];
                // 检查四个方向的相邻橘子
                for (int[] d : directions) {
                    int newR = r + d[0]; // 新的行位置
                    int newC = c + d[1]; // 新的列位置
                    // 确保新位置在网格内并且有新鲜的橘子
                    if (newR >= 0 && newR < rows && newC >= 0 && newC < cols && grid[newR][newC] == 1) {
                        grid[newR][newC] = 2; // 将新鲜橘子变为腐烂
                        queue.offer(new int[]{newR, newC}); // 将新腐烂的橘子加入队列
                        freshCount--; // 减少新鲜橘子的数量
                    }
                }
            }
            //完成一轮处理，时间+1
            minutesPassed++; // 每完成一轮处理，时间增加一分钟
        }

        // 判断是否还有新鲜橘子未被腐烂
        return freshCount == 0 ? minutesPassed : -1;
    }

/*    作者：Peter-Lu
    链接：https://leetcode.cn/problems/rotting-oranges/solutions/2739591/javaban-fu-lan-de-ju-zi-xiang-xi-de-zhu-2bjs7/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}

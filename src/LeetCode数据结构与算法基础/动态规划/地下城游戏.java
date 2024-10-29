package LeetCode数据结构与算法基础.动态规划;

import java.util.Arrays;


/**
 * 恶魔们抓住了公主并将她关在了地下城 dungeon 的 右下角 。地下城是由 m x n 个房间组成的二维网格。我们英勇的骑士最初被安置在 左上角 的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 *
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 *
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 *
 * 为了尽快解救公主，骑士决定每次只 向右 或 向下 移动一步。
 *
 * 返回确保骑士能够拯救到公主所需的最低初始健康点数。
 *
 * 注意：任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 *
 * 问初始生命值至少是多少才能救到公主
 */
class 地下城游戏 {

    /**
     * 我们希望「从出发点到当前点的路径和」尽可能大，而「从出发点到当前点所需的最小初始值」尽可能小。这两条路径各有优劣。
     * 从左上到右下同时考虑这两个点，则很难满足
     *
     * 反过来考虑从右下到左上。
     * 令 dp[i][j] 表示从坐标 (i,j) 到终点所需的最小初始值。
     *
     * 换句话说，当我们到达坐标 (i,j) 时，如果此时我们的路径和不小于 dp[i][j]，我们就能到达终点。
     * 这样一来，我们就无需担心路径和的问题，只需要关注最小初始值。对于 dp[i][j]，
     * 我们只要关心 dp[i][j+1] 和 dp[i+1][j] 的最小值 minn。记当前格子的值为 dungeon(i,j)，
     * 那么在坐标 (i,j) 的初始值只要达到 minn−dungeon(i,j) 即可。
     * 同时，初始值还必须大于等于 1。这样我们就可以得到状态转移方程：
     *
     * dp[i][j]=max(min(dp[i+1][j],dp[i][j+1])−dungeon(i,j),1)
     *
     * 最终答案即为 dp[0][0]。
     *
     * 边界条件为，当 i=n−1 或者 j=m−1 时，
     * dp[i][j] 转移需要用到的 dp[i][j+1] 和 dp[i+1][j] 中有无效值，
     * 因此代码实现中给无效值赋值为极大值。
     * 特别地，dp[n−1][m−1] 转移需要用到的 dp[n−1][m] 和 dp[n][m−1] 均为无效值，
     * 因此我们给这两个值赋值为 1。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/dungeon-game/solutions/326171/di-xia-cheng-you-xi-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[n][m - 1] = dp[n - 1][m] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);//上面和左边哪个更小。
                dp[i][j] = Math.max(minn - dungeon[i][j], 1);//更小的减去当前值，和1进行比较。
            }
        }
        return dp[0][0];
    }
}

/*作者：力扣官方题解
        链接：https://leetcode.cn/problems/dungeon-game/solutions/326171/di-xia-cheng-you-xi-by-leetcode-solution/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

package 校招笔试真题.完美世界;

/**
 * @author by KingOfTetris
 * @date 2023/9/23
 */
public class 凑零钱II {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int[] limits = {2, 3, 4};
        int amount = 10;
        int combinations = coinChange(coins, limits, amount);
        System.out.println("总共有 " + combinations + " 种组合方式");
    }

    public static int coinChange(int[] coins, int[] limits, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // 初始化动态规划数组，表示凑成金额为0的组合有1种
        int n = coins.length; // 硬币种类数量
        for (int i = 0; i < n; i++) {
            int coin = coins[i]; // 当前硬币的面值
            int limit = limits[i]; // 当前硬币的数量限制
            for (int j = amount; j >= coin; j--) {
                int maxCount = Math.min(limit, j / coin); // 当前硬币可使用的最大数量
                for (int k = 1; k <= maxCount; k++) {
                    dp[j] += dp[j - k * coin]; // 更新组合数量
                }
            }
        }
        return dp[amount]; // 返回凑成目标金额的组合数量
    }
}

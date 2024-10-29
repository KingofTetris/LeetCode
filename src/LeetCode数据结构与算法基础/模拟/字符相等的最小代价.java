package LeetCode数据结构与算法基础.模拟;


/**
 * 多多君最近在研究字符串之间的变换，可以对字符串进行若干次变换操作:
 * 交换任意两个相邻的字符，代价为0。
 * 将任意一个字符a修改成字符b，代价为 |a - b|（绝对值）。
 * 现在有两个长度相同的字符串X和Y，多多君想知道，如果要将X和Y变成两个一样的字符串，需要的最少的代价之和是多少。
 */
public class 字符相等的最小代价 {
    // 计算将字符串 X 转换为字符串 Y 的最小代价之和
    public static int minCost(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        // 创建一个二维数组 dp，dp[i][j] 表示将 X 的前 i 个字符转换为 Y 的前 j 个字符的最小代价之和
        int[][] dp = new int[m + 1][n + 1];
        // 初始化状态，空字符串之间的转换代价为 0
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + Math.abs(X.charAt(i - 1) - ' ');
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + Math.abs(' ' - Y.charAt(j - 1));
        }

        // 状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    // 当前字符相同，不需要代价
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 当前字符不同，计算三种操作的最小代价
                    // 直接从x 变到 y
                    //
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String X = "kitten";
        String Y = "ktiten";

        int minCost = minCost(X, Y);
        System.out.println("Minimum transformation cost: " + minCost);
    }
}

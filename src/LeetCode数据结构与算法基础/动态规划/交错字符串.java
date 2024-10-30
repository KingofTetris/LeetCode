package LeetCode数据结构与算法基础.动态规划;

/**
 * @author by KingOfTetris
 * @date 2024/10/30
 */
public class 交错字符串 {

    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        //如果n + m != t,t不然不可能由s1,s2交错组成。
        if (n + m != t) {
            return false;
        }
        /**
         * 我们可以用动态规划来求解。
         * 我们定义 f(i,j) 表示 s1 的前 i 个元素和 s2 的前 j 个元素是否能交错组成 s3 的前 i+j 个元素。
         * 如果 s1 的第 i 个元素和 s3 的第 i+j 个元素相等，
         * 那么 s1 的前 i 个元素和 s2 的前 j 个元素是否能交错组成 s3 的前 i+j 个元素
         * 取决于 s1 的前 i-1 个元素 和 s2 的前 j 个元素是否能交错组成 s3 的前 i+j-1 个元素，
         * 即此时 f(i,j) 取决于 f(i-1,j)，
         *
         * 在此情况下如果 f(i-1,j) 为真，则 f(i,j) 也为真。
         * 同样的，如果 s2 的第 j 个元素和 s3 的第 i+j 个元素相等并且 f(i,j-1) 为真，
         * 则 f(i,j) 也为真。于是我们可以推导出这样的动态规划转移方程：
         *
         * f(i,j) = [f(i-1,j) and s1(i-1) = s3(p)] or [f(i,j-1) and s2(j-1) = s3(p)]
         *
         * 其中 p=i+j−1。边界条件为 f(0,0)=True。至此，我们很容易可以给出这样一个实现：
         *
         */
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return dp[n][m];
    }
    /*
        作者：力扣官方题解
        链接：https://leetcode.cn/problems/interleaving-string/solutions/335373/jiao-cuo-zi-fu-chuan-by-leetcode-solution/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}

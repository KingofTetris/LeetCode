package LeetCode数据结构与算法基础.动态规划;

/**
 * @author by KingOfTetris
 * @date 2024/8/22
 */
public class 两个字符串的删除操作 {


    /**
     * 法一：我们直接用DP来解这道题，为了编辑距离做铺垫
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        /**
         * 这题的DP比较 不同的子序列(s字符串中有多少个不同的子序列等于t?) 又有点差别了
         *
         * 后者只有s可以删除，这题是s和t都能删除。
         *
         * 那么dp的含义也会有变化
         *
         */
        int n = word1.length(), m = word2.length();
        /**
         * 1.dp含义和下标
         * dp[i][j]：以s[i-1]为结尾的字符串word1，
         * 和以t[j-1]为结尾的字符串word2，想要达到相等，所需要删除元素的最少次数。
         */
        int[][] dp = new int[n + 1][m + 1];
        /**
         * 递推公式
         * if(word1[i - 1]  == word2[j - 1])，因为我们要找最少删除次数
         * 使得s 和 t相同，那么其实既然两个结尾都相同，也就没必要去动了。
         * 所以和前者保持一致
         * dp[i][j] = dp[i-1][j-1]
         *
         * if(word1[i - 1]  != word2[j - 1]) 那就比较麻烦了
         * 1.删除 word1[i - 1],最少操作次数为dp[i - 1][j] + 1
         * 2.删除 word2[j - 1],最少操作次数为dp[i][j - 1] + 1
         * 3.两个都删除，dp[i - 1][j - 1] + 2
         * 后面 +1 就相当于要进行一次删除操作。
         *
         * 那么dp[i][j] = Math.min(dp[i - 1][j] + 1,dp[i][j - 1] + 1,dp[i - 1][j - 1] + 2)
         */

        //初始值
        // dp[0][j] 也就是word[-1] 代表空的字符串，那么以word[j - 1]结尾的字符串要变为空字符串 就需要删掉j个元素
        // dp[0][j] = j
        // dp[i][0] 反过来空字符串也是一样，
        // dp[i][0] = i
        // 要注意dp 是n+1,m+1
        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = i;
        }
        /**
         * 遍历顺序 还是从 1，1开始
         */
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //三者的最小值
                    //Java不能直接min(a,b,c) 看着麻烦点
                    dp[i][j] = Math.min( Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + 2);
                }
            }
        }
        //注意返回值 就不是记录dp数组里面的最小值了
        //而是让 s t 两者相同的最少操作次数就是dp[n][m]
        return dp[n][m];
    }

    /**
     * 法二:简单的思路是求出两个字符串的最长公共子序列的长度l
     * 然后word1.length - l + word2.length - l 就是最少操作次数
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int l = longestCommonsubSequence(word1, word2);
        return n + m - 2 * l;
    }

    private int longestCommonsubSequence(String word1, String word2) {
        //dp含义
        /**
         * dp[i][j]：长度为[0, i - 1]的字符串text1
         * 与长度为[0, j - 1]的字符串text2的最长公共子序列为dp[i][j]
         * 这种公共子序列，子串都是这样定义，避免DP初始化麻烦
         */
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        //递推公式
        /**
         *  如果结尾字符相同 dp[i - 1][j - 1] + 1;
         *         if (char1[i - 1] == char2[j - 1]) { // 开始列出状态转移方程
         *             dp[i][j] = dp[i - 1][j - 1] + 1;
         *         }
         * 如果不同，那么就要取 Math.max(dp[i - 1][j], dp[i][j - 1]);
         *
         * 举例子看看
         * 比如
         * aec
         * ace
         *
         * 显然结尾 'c' 和 'e'不同
         *  dp[i][j] = max(dp[i-1][j],dp[i][j-1])
         */

        //初始值，观察递推公式
        /**
         * dp[i][j]
         * 由 i - 1，j - 1 ， [i-1][j]， [i][j-1]
         * 推出来
         *       (i-1,j-1),(i-1,j)
         *       (i,j-1)  , (i,j)
         * 那么(i,j)
         * 就会由下层和右边推过来，那么你就要初始化第一行，第一列
         */


        /**
         * dp[i][j]：长度为[0, i - 1]的字符串text1
         * 与长度为[0, j - 1]的字符串text2的最长公共子序列为dp[i][j]
         **/
        //初始化值其实无所谓，全是0，可以被公式覆盖 那也就没必要显示赋值了

        //循环
        int max = 0;
        //从1开始。因为第一行和第一列没有意义。
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                max = Math.max(dp[i][j], max);
            }
        }

        return max;
    }


}

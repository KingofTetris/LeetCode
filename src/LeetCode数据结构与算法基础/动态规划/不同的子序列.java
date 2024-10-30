package LeetCode数据结构与算法基础.动态规划;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/22
 */
public class 不同的子序列 {

    @Test
    public void test(){
        String s = "babgbag";
        String t = "bag";
        int numDistinct = numDistinct(s, t);
        System.out.println(numDistinct);
    }
    //s字符串中有多少个不同的子序列等于t?
    public int numDistinct(String s, String t) {
        //dp[i][j]：以i-1为结尾的s子序列中出现以j-1为结尾的t子序列的个数为dp[i][j]。
        /**
         * 这一类问题，基本是要分析两种情况
         *
         * s[i - 1] 与 t[j - 1]相等
         * s[i - 1] 与 t[j - 1] 不相等
         *
         * 1.当s[i - 1] 与 t[j - 1]相等时，dp[i][j]可以有两部分组成。
         *
         * 一部分是用s[i - 1]来匹配，那么个数为dp[i - 1][j - 1]。
         * 即因为末尾都相同，那么不需要考虑当前s子串和t子串的最后一位字母，所以只需要考虑 dp[i-1][j-1]。
         *
         * 另外一部分是不用s[i - 1]来匹配，个数为dp[i - 1][j]。
         *
         * 这里可能有录友不明白了，为什么还要考虑 不用s[i - 1]来匹配，都相同了指定要匹配啊。
         *
         * 例如： s：bagg 和 t：bag ，s[3] 和 t[2]是相同的，
         * 但是字符串s也可以不用s[3]来匹配，即用s[0]s[1]s[2]组成的bag。
         *
         * 当然也可以用s[3]来匹配，即：s[0]s[1]s[3]组成的bag。
         *
         * 所以当s[i - 1] 与 t[j - 1]相等时，dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
         *
         *
         * 2.当s[i - 1] 与 t[j - 1]不相等时，dp[i][j]只有一部分组成，
         * 不用s[i - 1]来匹配（就是模拟在s中删除这个元素），即：dp[i - 1][j]
         *
         * 所以递推公式为：dp[i][j] = dp[i - 1][j];
         */

        /**
         * 每次当初始化的时候，都要回顾一下dp[i][j]的定义，不要凭感觉初始化。
         *
         * dp[i][0]表示什么呢？
         *
         * dp[i][0] 表示：以i-1为结尾的s可以随便删除元素，出现空字符串的个数。
         *
         * 那么dp[i][0]一定都是1，因为也就是把以i-1为结尾的s，删除所有元素，出现空字符串的个数就是1。
         *
         * 再来看dp[0][j]，dp[0][j]：空字符串s可以随便删除元素，出现以j-1为结尾的字符串t的个数。
         *
         * 那么dp[0][j]一定都是0，s如论如何也变成不了t。
         *
         * 最后就要看一个特殊位置了，即：dp[0][0] 应该是多少。
         *
         * dp[0][0]应该是1，空字符串s，可以删除0个元素，变成空字符串t。
         */

        /**
         * 确定遍历顺序
         * 从递推公式dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
         * 和 dp[i][j] = dp[i - 1][j]; 中可以看出dp[i][j]都是根据左上方和正上方推出来的。
         */

        int[][] dp = new int[s.length() + 1][t.length() + 1];

        //初始化
        //dp[i][0]表示以为s[i-1]结尾的s，删除任意元素，出现空字符串的个数，那肯定是1
        //dp[0][j] 表示空字符串s任意删除元素，出现以t[j-1]结尾的字符串t的个数
        //显然空字符串不管怎么删也不可能变成t 所以都是0.
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 1;
        }

        //遍历方式
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
                //如果两个字符不相等，那么没办法，只能删掉s[i - 1]
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }

        return dp[s.length()][t.length()];
    }

}

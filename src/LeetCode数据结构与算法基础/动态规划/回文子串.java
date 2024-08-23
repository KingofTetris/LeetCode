package LeetCode数据结构与算法基础.动态规划;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/8/22
 */
public class 回文子串 {


    @Test
    public void test(){
        String s = "abccccdd";
        //badab
        int countSubstrings = countSubstrings(s);
        System.out.println(countSubstrings);
    }
    /**
     * 求出字符串s中有多少个回文串
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        //dp[i][j] 表示 [i,j] 这个子串是否是回文子串，是为true
        boolean[][] dp = new boolean[len][len];
        //递推公式
        /**
         * 要推出dp[i][j]的值
         * 就需要比较 s[i] s[j]
         * if(s[i] == s[j]){
         *  情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
         * 情况二：下标i 与 j相差为1，例如aa，也是回文子串
         *  上面两种统一为 (j - i) <= 1
         * 情况三：下标：i 与 j相差大于1的时候，例如cabac，
         * 此时s[i]与s[j]已经相同了，我们看i到j区间是不是回文子串就看aba是不是回文就可以了，
         * 那么aba的区间就是 i+1 与 j-1区间，这个区间是不是回文就看dp[i + 1][j - 1]是否为true。
         * }
         */
        int count = 0; //回文串数量
        int maxLen = 0; //最长回文串大小

        /**
         * 注意遍历顺序 从下到上，从左往右
         */
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {

                if (chars[i] == chars[j]) {
                    if (j - i <= 1) { // 情况一 和 情况二
                        count++;
                        dp[i][j] = true;
                    }
                    //j - i > 1 就需要判断[i+1,j-1]是不是回文串
                    //如果是就+1
                    else if (dp[i + 1][j - 1]) { //情况三
                        count++;
                        dp[i][j] = true;
                    }
                    //求最长长度
                    maxLen = Math.max(j - i + 1,maxLen);
                }
            }
        }
        System.out.println("回文串数量是" + count);
        System.out.println("其中最长的回文串长度是" + maxLen);
        return count;
    }
}

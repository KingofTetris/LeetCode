package LeetCode数据结构基础.day2.字符串;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 最长回文子串
 * @Time 2021/10/18  10:15
 */
/*给你一个字符串 s，找到 s 中最长的回文子串。
        示例 1：
        输入：s = "babad"
        输出："bab"
        解释："aba" 同样是符合题意的答案。
        示例 2：
        输入：s = "cbbd"
        输出："bb"
        示例 3：
        输入：s = "a"
        输出："a"
        示例 4：
        输入：s = "ac"
        输出："a"

        提示：
        1 <= s.length <= 1000
        s 仅由数字和英文字母（大写和/或小写）组成   */
public class 最长回文子串 {
    @Test
    public void test(){
//        String s = "dbmbadabdad";
        String s = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        System.out.println(longestPalindrome(s));
    }

    //暴力法，找到所有子串判断是否是回文串，取出最长的那个子串
    //String.substring(int begin,int end) 左闭右开
    //结果超时O(n^3)
    public String longestPalindrome(String s) {

        int maxLen = 0;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            //所以这里用 <=
            for (int j = i + 1; j <= s.length(); j++) {
                String sub = s.substring(i,j);
                if (isPalindromic(sub) && sub.length() > maxLen){
                    maxLen = sub.length();
                    res = sub;
                }
            }
        }
        return res;
    }

    //判断是否是回文串
    public boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }


    //动态规划
   /* public String longestPalindrome(String s) {
        int len = s.length();
        // 特判
        if (len < 2){
            return s;
        }

        int maxLen = 1;
        int begin  = 0;

        // 1. 状态定义
        // dp[i][j] 表示s[i...j] 是否是回文串


        // 2. 初始化
        boolean[][] dp = new boolean[len][len];

        //初始化设置s[i.....j]都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        // 3. 状态转移
        // 注意：先填左下角
        // 填表规则：先一列一列的填写，再一行一行的填，保证左下方的单元格先进行计算
        // 一列一列填写就是[0,1] [0,2],[0,3].........[0,n-1]
        for (int j = 1;j < len;j++){
            for (int i = 0; i < j; i++) {
                // 头尾字符不相等，不是回文串
                if (chars[i] != chars[j]){
                    dp[i][j] = false;
                }else {
                    // 相等的情况下
                    // 考虑头尾去掉以后没有字符剩余，或者剩下一个字符的时候，肯定是回文串
                    if (j - i < 3){
                        dp[i][j] = true;
                    }else {
                        // 状态转移
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要dp[i][j] == true 成立，表示s[i...j] 是否是回文串
                // 此时更新记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        // 4. 返回值
        return s.substring(begin,begin + maxLen);
    }*/
}

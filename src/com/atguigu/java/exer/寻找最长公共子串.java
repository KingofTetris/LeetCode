package com.atguigu.java.exer;


import org.junit.Test;

/**
 * 寻找a,b的最长公共字串
 */
public class 寻找最长公共子串 {
    @Test
    public void test(){
        String a="adsadasddhelloworldsss";
        String b="cchelloworldsss";
        int longestCommonSubstr2 = findLongestCommonSubstr2(a, b);
        System.out.println(longestCommonSubstr2);
    }

    /**
     * 简单暴力法 先找到所有短字符的子串就是O(n^2) 然后判断长字符包不包含这些子串 又是 O(n^2)
     * 最后实际上是 O(n^4)
     * @param str1
     * @param str2
     */
    public void findLongestCommonSubstr(String str1,String str2) {

        String longStr;
        String shortStr;
        int len1 = str1.length();
        int len2 = str2.length();
        if(len1 > len2){
            longStr = str1;
            shortStr = str2;
        }
        else{
            longStr = str2;
            shortStr = str1;
        }

        String TheLongestCommonStr = "";

        //暴力法 用短字符串的所有子串subStr 一个一个去判断longStr包不包含subStr,包含而且更长就更新为subStr
        for (int i = 0; i < shortStr.length(); i++) {
            for (int j = i+1; j < shortStr.length(); j++) {
                String subStr = shortStr.substring(i,j+1);
                if(longStr.contains(subStr) && subStr.length() > TheLongestCommonStr.length())
                    TheLongestCommonStr = subStr;
            }
        }
        System.out.println(TheLongestCommonStr);

    }

    /**
     * ​ 使用经典的动态规划方法，首先定义一个动态规划数组dp，
     * dp[i][j]表示的含义是以在str1中第i个字符和str2中第j个字符结尾的最长公共字串的长度。那么状态转移表达式就是：
     *
     */
    public int findLongestCommonSubstr2(String str1,String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) return 0;
        int max = 0;
        // +1是为了防止边界条件判断 和 减少初始化当 i 或 j 等于 0 时，初始化子串就是为0，初始化数组也是为0
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = 0;
                //最大值不一定出现在数组的最后一个位置，所以要用一个临时变量记录下来。
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}

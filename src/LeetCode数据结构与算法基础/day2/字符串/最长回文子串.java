package LeetCode数据结构与算法基础.day2.字符串;

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
    public void test() {
        String s = "dbmbadabdad";
        String res = longestPalindrome2(s);
        System.out.println(res);
        System.out.println(res.length());
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
                String sub = s.substring(i, j);
                if (isPalindromic(sub) && sub.length() > maxLen) {
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


    /**
     * 本题的AC做法是通过DP求最长回文子串。
     * 本题的DP其实是中心扩散法的延申，我们先讲中心扩散
     * @param
     * @return
     */
    static int range[] = new int[2];

    public static String longestPalindrome2(String str) {
        if (str == null) return null;
        int n = str.length();
        if (n == 1 || n == 0) return str;
        //中心扩散法
        //回文串中心要么是i,要么是i和i-1
        char[] ss = str.toCharArray();//不要用charAt(i)，实验发现比你直接从数组取要慢。
        for (int i = 0; i < n; i++) {
            helper(ss, n, i, i);
            helper(ss, n, i - 1, i);
        }
        return str.substring(range[0],range[1]);
    }

    public static void helper(char[] ss, int n, int start, int end) {
        while (start >= 0 && end <= n- 1){
            if (ss[start] == ss[end]){
                start--;
                end++;
            }else {
                break; //如果不回文直接break;
            }
        }
        //更新最大子串
        //start+1 才是回文串的起始,因为返回的是substring.就没必要end-1了。
        if (end - (start + 1) > range[1] - range[0]){
            range[0] = start + 1;
            range[1] = end;
        }
    }

    //还有一种奇思妙想，把字符串反过来和原来的字符串比较，是否对称。
    //这样的解法对于LeetCode最后一个测试用例无法通过。
    public static String longestPalindrome3(String s) {
        return "";
    }
}

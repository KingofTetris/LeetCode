package 剑指offer第二版.字符串;

import org.junit.Test;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author KingofTetris
 * @Date 2022/7/21 14:32
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
 * 而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer19_正则表达式匹配 {

    @Test
    public void test() {
        String s = "h#a";
        String p = "h*?*a";
        char[] chars = p.toCharArray();
        LinkedList<Integer> remove = new LinkedList<>();
        for (int i = 1; i < chars.length; i++) {
            while (chars[i] == '*' && chars[i - 1] == '?') {
                remove.add(i);
                i++;
            }
            while (chars[i] == '*' && chars[i - 1] == '*') {
                remove.add(i);
                i++;
            }
        }
        LinkedList<Character> res = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            if (!remove.contains(i)) {
                res.add(chars[i]);
            }
        }
//        System.out.println("res = " + res);
        StringBuilder sb = new StringBuilder();
        for (Character re : res) {
            sb.append(re);
        }
        Pattern pattern = Pattern.compile(sb.toString());
        Matcher m = pattern.matcher(s);
        System.out.println(m.matches());
    }

    /**
     * 实现.和*的正则匹配 。 .表示任意字符，*表示前一个字符可以出现0或任意多次
     *
     * @param s s 可能为空，且只包含从 a-z 的小写字母。
     * @param p p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
     *          <p>
     *          TODO 我看不懂，但是我大受震撼。动态规划里面比较难的题目。
     * @return
     */

    public boolean isMatch(String s, String p) {
//        boolean flag = s.matches(p);
//        return flag;
        if (s == null || p == null) return false;
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        //dp初始值
        dp[0][0] = true;
        // dp[0][1] = false;
        for (int j = 2; j <= m; j++) { //j>=2的情况
            if (p.charAt(j - 1) == '*') { //如果j的位置是*号 可以代表0到N个字符 就相当于把 * 和 j - 1消除了。那么就去判断dp[0][j-2]
                dp[0][j] = dp[0][j - 2];
            } else {
                dp[0][j] = false;//其他情况都是false。其实这句话没必要写，因为初始就全是false
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //当j不为*号。为普通字符或者.
                if (p.charAt(j - 1) != '*') {
                    if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
                //如果j为*号，那么再次分类讨论
                else {
                    //1. j-1个字符不匹配
                    // 例如 a
                    //     b*
                    if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else { //匹配又分成匹配0，1，N个
                        dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j];//三个成立一个就可以
                    }
                }
            }
        }
        return dp[n][m];
    }
}

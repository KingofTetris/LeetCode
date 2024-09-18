package 剑指offer第二版.字符串;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/6 17:16
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * 示例 1:
 * 输入：s = "abaccdeff"
 * 输出：'b'
 * 示例 2:
 * 输入：s = ""
 * 输出：' '
 * 限制：
 * 0 <= s 的长度 <= 50000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer50_第一个只出现一次的字符 {

    @Test
    public void test(){
        String s = "";
        System.out.println(firstUniqChar(s));
    }

    /**
     *  s 只包含小写字母。
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            //charAt直接返回value[i]，O(1)
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1)
                return s.charAt(i);
        }
        return ' ';
    }
}
